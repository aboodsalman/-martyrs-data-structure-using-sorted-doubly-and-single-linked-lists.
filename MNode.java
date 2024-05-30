package Phase1;

public class MNode {
	private Object key;
	private MNode next;
	
	public MNode(Object key) {
		super();
		this.key = key;
	}
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public MNode getNext() {
		return next;
	}
	public void setNext(MNode next) {
		this.next = next;
	}
	
	
}
