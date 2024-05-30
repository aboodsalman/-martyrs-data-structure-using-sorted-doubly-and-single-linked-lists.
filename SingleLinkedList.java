package Phase1;

public class SingleLinkedList {
	private MNode first, last;
	private int count=-1;
	
	public MNode getFirst() {
		return first;
	}

	public Object getLast() {
		if(last==null) return null;
		return last.getKey();
	}

	public void addFirst(Object data) {
		MNode MNode = new MNode(data);
		if(count==-1) first=last=MNode;
		else {
			MNode.setNext(first);
			first=MNode;
		}
		count++;
	}

	public void addLast(Object key) {
		MNode node = new MNode(key);
		if(first==null) {
			first=last=node;
			last.setNext(first);
		}
		else {
			last.setNext(node);
			last=node;
			last.setNext(first);
		}
		count++;
	}
	
	public void add(Object data, int index) {
		MNode MNode = new MNode(data);
		if(index<=0) addFirst(data);
		else if(index>count) addLast(data);
		else {
			MNode temp = first;
			for(int i=0; i<index-1; i++) temp=temp.getNext();
			MNode.setNext(temp.getNext());
			temp.setNext(MNode);
			count++;
		}
	}

	public boolean removeFirst() {
		if(first==null) return false;
		if(first==last) first=last=null;
		else {
			MNode temp = first;
			first=first.getNext();
			temp.setNext(null);
		}
		count--;
		return true;
	}

	public boolean removeLast() {
		if(first==null) return false;
		if(first==last) first=last=null;
		else {
			MNode temp=first;
			for(int i=0; i<count-1; i++) temp=temp.getNext();
			temp.setNext(null);
			last=temp;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		if(index<0 || index>count) return false;
		if(index==0) return removeFirst();
		if(index==count) return removeLast();
		else {
			MNode temp = first;
			for(int i=0; i<index-1; i++) temp=temp.getNext();
			MNode trash = temp.getNext();
			temp.setNext(temp.getNext().getNext());
			trash.setNext(null);
			count--;
			return true;
		}
	}

	public boolean remove(Object data) {
		if(first==null) return false;
		if(first.getKey().equals(data)) return removeFirst();
		if(last.getKey().equals(data)) return removeLast();
		else {
			MNode temp = first;
			for(int i=0; i<count; i++) {
				if(temp.getKey().equals(data)) return remove(i);
				temp=temp.getNext();
			}
		}
		return false;
	}

	public String printList() {
		MNode temp = first;
		String s="";
		for(int i=0; i<=count; i++) {
			if(i!=count) s+=temp.getKey()+" -> ";
			else s+=temp.getKey();
			temp=temp.getNext();
		}
		return s;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
