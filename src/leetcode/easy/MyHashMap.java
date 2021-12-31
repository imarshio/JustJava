package easy;



/**
* @arithmetics ：
* @author ： masuo
* @time ：2021年3月14日 上午11:33:49
* @类说明 :不使用任何内建的哈希表库设计一个哈希映射（HashMap）。key:value.
* 
*/
class MyHashMap {
	private class Hashmap{
		private int hkey;
		private int hvalue;
		private void setValue(int hvalue) {
			this.hvalue = hvalue;
		}
		
		private int getHkey() {
			return hkey;
		}
		private int getHvalue() {
			return hvalue;
		}
	}
	public static void main(String[] args) {
		
	}
	
	/** Initialize your data structure here. */
    public MyHashMap() {
    	Hashmap hashmap = new Hashmap();
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
    	
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
		return key;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {

    }
}


