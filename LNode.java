package Phase1;

public class LNode {
	private Object key;
	private LNode next;
	private MNode MarHead, MarTail;
	
	public LNode(Object key) {
		super();
		this.key = key;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public LNode getNext() {
		return next;
	}

	public void setNext(LNode next) {
		this.next = next;
	}

	public MNode getMarHead() {
		return MarHead;
	}

	public void setMarHead(MNode marHead) {
		MarHead = marHead;
	}

	public MNode getMarTail() {
		return MarTail;
	}

	public void setMarTail(MNode marTail) {
		MarTail = marTail;
	}
	
	
}
