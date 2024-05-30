package Phase1;

public class LinkedList {
	private DNode first, last;
	private int count=-1;
	
	public DNode getFirst(){
		return first;
	}
	
	public DNode getLast() {
		return last;
	}
	
	// adding an element to the beginning to the linked list
	public boolean addFirst(String city) {
		DNode node = new DNode(city);
		if(first==null) first=last=node;
		else {
			node.setNext(first);
			first.setPrev(node);
			first=node;
		}
		last.setNext(first);
		first.setPrev(last);
		count++;
		return true;
	}
	
	// adding an element to the end of the linked list
	public boolean addLast(String city) {
		DNode node = new DNode(city);
		if(first==null) first=last=node;
		else{
			last.setNext(node);
			node.setPrev(last);
			last=node;
		}
		last.setNext(first);
		first.setPrev(last);
		count++;
		return true;
	}
	
	// find a specific city by its name
	public DNode findCity(String city) {
		if(first==null) return null;
		if(((String)first.getKey()).toLowerCase().equals(city.toLowerCase())) return first;
		DNode node = first.getNext();
		while(node!=first && !((String)node.getKey()).toLowerCase().equals(city.toLowerCase())) node = node.getNext();
		if(node==first) return null;
		return node;
	}
	
	// find a specific location by its name
	public LNode findLoc(String loc, String city) {
		DNode cnode = findCity(city);
		if(cnode.getLocHead()==null) return null;
		if(((Location)(cnode.getLocHead().getKey())).getName().toLowerCase().equals(loc.toLowerCase())) return cnode.getLocHead();
		LNode temp = cnode.getLocHead().getNext();
		while(temp!=cnode.getLocHead() && !((Location)temp.getKey()).getName().toLowerCase().equals(loc.toLowerCase())) temp=temp.getNext();
		if(temp==cnode.getLocHead()) return null;
		return temp;
	}
	
	// find a specific martyr by his/her name
	public MNode findMartyr(Martyr martyr, String region, String city) {
		LNode lnode = findLoc(region, city);
		if(lnode.getMarHead()==null) return null;
		if(((Martyr)lnode.getMarHead().getKey()).getName().toLowerCase().equals(martyr.getName().toLowerCase())) return lnode.getMarHead();
		MNode temp = lnode.getMarHead();
		do {
			if(((Martyr)temp.getKey()).getName().toLowerCase().equals(martyr.getName().toLowerCase()))break;
			temp=temp.getNext();
		} while(temp!=lnode.getMarHead());
		if(temp==lnode.getMarHead()) return null;
		return temp;
	}
	
	// adding a node of a city to the doubly linked list
	public boolean addCityNode(DNode city){
		if(findCity(((String)city.getKey()))!=null) return false;
		if(first==null) {
			count++;
			first=last=city;
			last.setNext(first);
			first.setPrev(last);
			return true;
		}
		else if(((String)first.getKey()).toLowerCase().compareTo(((String)city.getKey()).toLowerCase())>0) {
			city.setNext(first);
			first.setPrev(city);
			city.setPrev(last);
			last.setNext(city);
			first=city;
			count++;
		}
		else if(((String)last.getKey()).toLowerCase().compareTo(((String)city.getKey()).toLowerCase())<0) {
			city.setNext(first);
			first.setPrev(city);
			city.setPrev(last);
			last.setNext(city);
			last=city;
			count++;
		}
		else {
			DNode temp=first;
			for(int i=0; i<count; i++) {
				if(((String)temp.getKey()).toLowerCase().compareTo(((String)city.getKey()).toLowerCase())>0) break;
				temp = temp.getNext();
			}
			city.setNext(temp);
			city.setPrev(temp.getPrev());
			temp.setPrev(city);
			city.getPrev().setNext(city);
			count++;
		}
		return true;
	}
	
	// adding a location node to the linked list
	public boolean addLocationNode(LNode location, DNode city){
		if(findLoc(((Location)location.getKey()).getName(), (String)city.getKey())!=null) return false;
		if(city.getLocHead()==null) {
			city.setLocHead(location);
			city.setLocTail(location);
			city.getLocTail().setNext(city.getLocHead());
		} else if(((Location)city.getLocHead().getKey()).getName().toLowerCase().compareTo(((Location)location.getKey()).getName().toLowerCase())>0) {
			location.setNext(city.getLocHead());
			city.getLocTail().setNext(location);
			city.setLocHead(location);
		} else if(((Location)city.getLocTail().getKey()).getName().toLowerCase().compareTo(((Location)location.getKey()).getName().toLowerCase())<0) {
			location.setNext(city.getLocHead());
			city.getLocTail().setNext(location);
			city.setLocTail(location);
		} else {
			LNode temp=city.getLocHead();
			do {
				if(((Location)temp.getNext().getKey()).getName().toLowerCase().compareTo(((Location)location.getKey()).getName().toLowerCase())>0) 
					break;
				temp = temp.getNext();
			} while(temp.getNext()!=city.getLocHead());
			location.setNext(temp.getNext());
			temp.setNext(location);
		}
		return true;
	}
	
	// adding a new city to the doubly linked list
	public boolean addCity(String city){
		if(findCity(city)!=null) return false;
		DNode node = new DNode(city);
		if(first==null) {
			count++;
			first=last=node;
			last.setNext(first);
			first.setPrev(last);
			return true;
		}
		else if(((String)first.getKey()).toLowerCase().compareTo(city.toLowerCase())>0) return addFirst(city);
		else if(((String)last.getKey()).toLowerCase().compareTo(city.toLowerCase())<0) return addLast(city);
		else {
			DNode temp=first;
			for(int i=0; i<count; i++) {
				if(((String)temp.getKey()).toLowerCase().compareTo(city.toLowerCase())>0) break;
				temp = temp.getNext();
			}
			node.setNext(temp);
			node.setPrev(temp.getPrev());
			temp.setPrev(node);
			node.getPrev().setNext(node);
			count++;
			return true;
		}
	}
	
	// adding a new location to the doubly linked list
	public boolean addRegion(Location region, String city) {
		if(findLoc(region.getName(), city)!=null) return false;
		DNode node = findCity(city);
		
		LNode temp = node.getLocHead();
		LNode reg = new LNode(region);
		if(temp==null) {
			node.setLocHead(reg);
			node.setLocTail(reg);
			node.getLocTail().setNext(reg);
			return true;
		}
		else if(((Location)temp.getKey()).getName().toLowerCase().compareTo(region.getName().toLowerCase())>0) {
			reg.setNext(temp);
			node.setLocHead(reg);
			node.getLocTail().setNext(reg);
			return true;
		}
		else if(((Location)node.getLocTail().getKey()).getName().toLowerCase().compareTo(region.getName().toLowerCase())<0) {
			reg.setNext(temp);
			node.getLocTail().setNext(reg);
			node.setLocTail(reg);
			return true;
		}
		else{
			while(temp.getNext()!=node.getLocHead()) {
				if(((Location)temp.getNext().getKey()).getName().toLowerCase().compareTo(region.getName().toLowerCase())>0) break;
				temp = temp.getNext();
			}
			reg.setNext(temp.getNext());
			temp.setNext(reg);
		}
		return true;
	}
	
	// adding a new martyr record to the linked list
	public boolean addMartyr(Martyr mar, Location region, String city) {
		if(findMartyr(mar, region.getName(), city)!=null) return false;
		
		LNode lnode = findLoc(region.getName(), city);
		
		MNode temp = lnode.getMarHead();
		MNode mnode = new MNode(mar);
		if(temp==null) {
			lnode.setMarHead(mnode);
			lnode.setMarTail(mnode);
			lnode.getMarTail().setNext(mnode);
		}
		else if(((Martyr)temp.getKey()).getAge() >= mar.getAge()) {
			mnode.setNext(temp);
			lnode.setMarHead(mnode);
			lnode.getMarTail().setNext(mnode);
		}
		else if(((Martyr)lnode.getMarTail().getKey()).getAge() <= mar.getAge()) {
			lnode.getMarTail().setNext(mnode);
			lnode.setMarTail(mnode);
			mnode.setNext(lnode.getMarHead());
		}
		else{
			while(temp.getNext()!=lnode.getMarHead()) {
				if(((Martyr)(temp.getNext().getKey())).getAge() >= mar.getAge()) break;
				temp = temp.getNext();
			}
			mnode.setNext(temp.getNext());
			temp.setNext(mnode);
		}
		if(mar.getGender().charAt(0)=='M') ((Location)lnode.getKey()).setMales(((Location)lnode.getKey()).getMales()+1);
		else ((Location)lnode.getKey()).setFemales(((Location)lnode.getKey()).getFemales()+1);
		return true;
	}
	
	// delete the first node from the linked list
	public boolean removeFirst() {
		if(first==null) return false;
		DNode temp = first;
		first = first.getNext();
		first.setPrev(last);
		last.setNext(first);
		temp.setNext(null);
		temp.setPrev(null);
		count--;
		return true;
	}
	
	// delete the last node from the linked list
	public boolean removeLast() {
		if(first==null) return false;
		DNode temp = last;
		last = last.getPrev();
		last.setNext(first);
		first.setPrev(last);
		temp.setNext(null);
		temp.setPrev(null);
		count--;
		return true;
	}
	
	// delete a city by its name
	public boolean deleteCity(String city) {
		DNode node = findCity(city);
		if(node==null) return false;
		if(node==first) return removeFirst();
		if(node==last) return removeLast();
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		node.setNext(null);
		node.setPrev(null);
		count--;
		return true;
	}
	
	// print the linked list
	public String printList(String loc, String city) {
		String s="";
		LNode temp = findLoc(loc, city);
		MNode node = temp.getMarHead();
		do {
			s+=((Martyr)node.getKey()).getName()+" ";
			node = node.getNext();
		} while(node!=temp.getMarHead());
		return s;
	}
	
	// give the total number of males in a a specific district
	public int totalDistrictMales(String city) {
		DNode node = findCity(city);
		LNode loc = node.getLocHead();
		if(loc==null) return 0;
		int count=0;
		do {
			count+=((Location)loc.getKey()).getMales();
			loc=loc.getNext();
		} while(loc!=node.getLocHead());
		
		return count;
	}
	
	// give the total number of females in a a specific district
	public int totalDistrictFemales(String city) {
		DNode node = findCity(city);
		LNode loc = node.getLocHead();
		if(loc==null) return 0;
		int count=0;
		do {
			count+=((Location)loc.getKey()).getFemales();
			loc=loc.getNext();
		} while(loc!=node.getLocHead());
		
		return count;
	}
	
	// give the average of ages of martyrs of a specific district
	public double averageAge(String city) {
		DNode node = findCity(city);
		LNode loc = node.getLocHead();
		if(loc==null) return 0;
		MNode mar;
		double sum=0, counter=0;
		do {
			mar=loc.getMarHead();
			if(mar==null) return 0;
			do {
				sum+=((Martyr)mar.getKey()).getAge();
				counter++;
				mar=mar.getNext();
			}while(mar!=loc.getMarHead());
			loc=loc.getNext();
		}while(loc!=node.getLocHead());
		
		return ((double)(int)((sum/counter)*100))/100;
	}
	
	// give the date which have the maximum number of martyrs for a specific district
	public String maxDate(String city) {
		DNode node = findCity(city);
		LNode loc = node.getLocHead();
		if(loc==null) return "none";
		int mx=0, counter=0;
		String maxD="";
		MNode mar=loc.getMarHead();
		do {
			mar=loc.getMarHead();
			do {
				if(!((Martyr)mar.getKey()).isFlag()) {
					counter=countByDate(city, ((Martyr)mar.getKey()).getDate());
					if(counter>mx) {
						mx=counter;
						maxD=((Martyr)mar.getKey()).getDate();
					}
				}
				((Martyr)mar.getKey()).setFlag(false);
				mar=mar.getNext();
			} while(mar!=loc.getMarHead());
			loc=loc.getNext();
		} while(loc!=node.getLocHead());
		return maxD;
	}
	
	// give the total number of martyrs for a a specific date in the district
	public int countByDate(String city, String date) {
		DNode node = findCity(city);
		LNode loc = node.getLocHead();
		int counter=0;
		do {
			MNode mar = loc.getMarHead();
			do {
				if(((Martyr)mar.getKey()).getDate().equals(date)) {
					((Martyr)mar.getKey()).setFlag(true);
					counter++;
				}
				mar=mar.getNext();
			}while(mar!=loc.getMarHead());
			loc = loc.getNext();
		}while(loc!=node.getLocHead());
		return counter;
	}
	
	// delete a location record from the linked list
	public boolean deleteLocation(LNode loc, String city) {
		if(findLoc(((Location)loc.getKey()).getName(), city)==null) return false;
		DNode node = findCity(city);
		LNode temp = node.getLocHead();
		if(((Location)loc.getKey()).getName().equals(((Location)temp.getKey()).getName())){
			node.getLocTail().setNext(temp.getNext());
			node.setLocHead(temp.getNext());
			temp.setNext(null);
			return true;
		}
		do {
			if(((Location)temp.getNext().getKey()).getName().equals(((Location)loc.getKey()).getName())) break;
			temp=temp.getNext();
		} while(temp.getNext()!=node.getLocHead());
		temp.setNext(loc.getNext());
		loc.setNext(null);
		if(temp.getNext()==node.getLocTail()) node.setLocTail(temp);
		return true;
	}
	
	// delete a martyr record from the linked list
	public boolean deleteMartyr(Martyr mar, String loc, String city) {
		MNode mnode = findMartyr(mar, loc, city);
		if(mnode==null) return false;
		LNode node = findLoc(loc, city);
		MNode temp = node.getMarHead();
		if(mar.getName().equals(((Martyr)temp.getKey()).getName())){
			node.getMarTail().setNext(temp.getNext());
			node.setMarHead(temp.getNext());
			temp.setNext(null);
			return true;
		}
		do {
			if(((Martyr)temp.getNext().getKey()).getName().equals(mar.getName())) break;
			temp=temp.getNext();
		} while(temp.getNext()!=node.getMarHead());
		temp.setNext(mnode.getNext());
		mnode.setNext(null);
		if(temp.getNext()==node.getMarHead()) node.setMarTail(temp);
		return true;
	}
	
	// get the average of ages of the martyrs of a specific location
	public double agesAverageLocation(LNode loc) {
		MNode mar = loc.getMarHead();
		if(mar==null) return 0.0;
		double sum=0, counter=0;
		do {
			sum+=((Martyr)mar.getKey()).getAge();
			counter++;
			mar=mar.getNext();
		}while(mar!=loc.getMarHead());
		return ((double)(int)((sum/counter)*100))/100;
	}
	
	// search for martyrs of a specific district by a part of their names
	public MNode searchPartName(String name, String district) {
		SingleLinkedList sll = new SingleLinkedList();
		DNode temp = findCity(district);
		LNode loc = temp.getLocHead();
		if(loc==null) return null;
		MNode node;
		do {
			node = loc.getMarHead();
			if(node==null) return null;
			do {
				if(((Martyr)node.getKey()).getName().contains(name)) sll.addLast((Martyr)node.getKey());
				node = node.getNext();
			} while(node!=loc.getMarHead());
			loc = loc.getNext();
		} while(loc!=temp.getLocHead());
		
		return sll.getFirst();
	}
	
	// give the youngest and the oldest martyr of a specific location
	public String youngestOldestMartyr(LNode node) {
		int mx=0, mn=200;
		String young="", old="";
		MNode mar = node.getMarHead();
		if(mar==null) return "none,none";
		do {
			if(((Martyr)mar.getKey()).getAge()>mx) {
				mx=((Martyr)mar.getKey()).getAge();
				old=((Martyr)mar.getKey()).getName();
			}
			if(((Martyr)mar.getKey()).getAge()<mn) {
				mn=((Martyr)mar.getKey()).getAge();
				young=((Martyr)mar.getKey()).getName();
			}
			mar=mar.getNext();
		}while(mar!=node.getMarHead());
		
		return young+","+old;
	}
	
	// to change the district name of specific martyrs
	public void changeMartyrsDistrict(DNode node) {
		LNode loc = node.getLocHead();
		if(loc==null) return;
		do {
			MNode mar = loc.getMarHead();
			if(mar==null) return;
			do {
				((Martyr)mar.getKey()).setDistrict((String)node.getKey());
				mar=mar.getNext();
			} while(mar!=loc.getMarHead());
			loc=loc.getNext();
		} while(loc!=node.getLocHead());
	}
	
	// to change the location name of specific martyrs
	public void changeMartyrsLocation(LNode node) {
		MNode mar = node.getMarHead();
		if(mar==null) return;
		do {
			((Martyr)mar.getKey()).setLocation(((Location)node.getKey()).getName());
			mar=mar.getNext();
		} while(mar!=node.getMarHead());
	}
}


