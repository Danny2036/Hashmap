
public class Hashnode {
	private String key;
	private int num;
	private Hashnode next;

	/*
	 * Constructor
	 * @param key: represents the key value of the hashnode
	 */
	public Hashnode(String key){
		this.key = key;
		num = 1;
		next = null;
	}

	public String getKey(){
		if(this.key == null){
			return null;
		}
		return this.key;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public void addNum(){
		this.num++; 
	}

	public void setNext(Hashnode node){
		this.next = node;
	}
	
	public Hashnode getNext(){
		return this.next;
	}
}
