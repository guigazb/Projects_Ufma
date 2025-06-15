//Observe que a entrada de dados poderá ser de caracteres/string ou números. Você deverá
//escolher o método de tratamento de colisões e a função de hashing que será utilizada.

public class HashTentativaquadratica<Key, Value> {
	
    private int N; // numero de pares de chaves na tabela
	private int M = 512; // Tamanho da tabela hash com tratamento linear
	private Key[] keys; // chaves
	private Value[] vals; // valores
	
	@SuppressWarnings("unchecked")
	public HashTentativaquadratica() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	@SuppressWarnings("unchecked")
	public HashTentativaquadratica(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
		M = cap;
	}
	
	
	//Calcula o Hash 

    private int hash(Key key) {

        int h = key.hashCode();
        
		// mistura os bits para melhorar uniformidade
		h ^= (h >>> 20) ^ (h >>> 12);
        h ^= (h >>> 7) ^ (h >>> 4);
        
		// garante que não seja negativo
		return (h & 0x7fffffff) % M;
    }

    
	// Insere um novo objeto no Hash  - sondagem quadrática para resolver colisões

    public void put(Key key, Value val){

        if(N >= M / 2){
			resize(2 * M);
		}

        int i;
        int probe = 1;

        for (i = hash(key); keys[i] != null; i = (i + probe * probe) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            probe++;
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    
	// Redimensiona a tabela de acordo com a quantidade de chaves.

    private void resize(int cap) {

        HashTentativaquadratica<Key, Value> t = new HashTentativaquadratica<>(cap);
        
		for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        
		keys = t.keys;
        vals = t.vals;
        M = t.M;
    }
	
	
	public boolean contains(Key key) {
	    
		if (key == null) {
	        throw new IllegalArgumentException("Argument to contains() cannot be null");
	    }

	    return get(key) != null;
	}
	
	
	//Remove um objeto do Hash
	
	public void delete(Key key){

		if (key == null){
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		}
			
		if (!contains(key)){
			return;
		}
			
			
		int i = hash(key);

		while (!key.equals(keys[i])){
			i = (i + 1) % M;
		}
			
		
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % M;
		
		while (keys[i] != null){
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}

		N--;

		if (N > 0 && N == M/8){
			resize(M/2);
		}
			
	}
	

	//Busca um objeto no Hash
	
	public Value get(Key key) {

        if (key == null){
			throw new IllegalArgumentException("Argument to get() cannot be null");
		}
            
        
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
			if (keys[i].equals(key)){
				return vals[i];
			}      
		}
            

        return null;
	}
}