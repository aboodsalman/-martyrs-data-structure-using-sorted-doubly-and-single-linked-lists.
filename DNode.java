package Phase1;

public class DNode implements Comparable<String>{
	private Object key;
	private DNode next, prev;
	private LNode LocHead, LocTail;
	
	public DNode(String key) {
		super();
		this.key = key;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public LNode getLocHead() {
		return LocHead;
	}

	public void setLocHead(LNode locHead) {
		LocHead = locHead;
	}

	public LNode getLocTail() {
		return LocTail;
	}

	public void setLocTail(LNode locTail) {
		LocTail = locTail;
	}

	@Override
	public int compareTo(String o) {
		return 0;
	}
	
	
}
