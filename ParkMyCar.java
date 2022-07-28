import java.util.*;
public class ParkMyCar
{
	Map<Integer, Park> map = new TreeMap<>();
	static int allocateSlot = 0;
	static int totalSlots = 0;

	int addIfSlotIsPresent(String s1, String s2){
    	Set<Integer> keys=new TreeSet<Integer>();  
		keys = map.keySet();
   		for (int i=1; i<=totalSlots;i++){ 
        	if(!keys.contains(i)){
        		Park p2 = new Park(i,s1,s2);
        		map.put(i, p2);
        		return i;
        	}
        }
       return 0;	
    }

	int parkCar(String s1, String s2){
		Park p2 = new Park(allocateSlot++,s1,s2);
		map.put(allocateSlot, p2);
		return allocateSlot;
    }

    int totalAllocatedSlots(){
    	Set<Integer> keys=new HashSet<Integer>();  
		keys = map.keySet();
		List<Integer> list = new ArrayList<>();
		list.addAll(keys); 
		Collections.sort(list, Collections.reverseOrder());
		if(list.isEmpty()){
			return 0;
		}
	  	return list.get(0);
    }

    void removeSlot(int slotNo){
    	 map.remove(slotNo);
    }

    void printStatus(){
    	System.out.println("Slot No. Registration No. Colour");
    	map.forEach((id, carData)
    		-> System.out.println(id +" "+carData.getRegistrationNo()+" "+carData.getColour()));

    }

    void findRegistrationNoByColour(String colour){
		map.forEach((id, carData) -> {
		if(carData.getColour().equals(colour)){
			System.out.print(carData.getRegistrationNo()+" ");
		}
		});
		System.out.println();
    }

    void findIdByColour(String colour){
		map.forEach((id, carData) -> {
			if(carData.getColour().equals(colour)){
				System.out.print(id+" ");
			}
		});
		System.out.println();
    } 

    boolean findSlotNoByRegistrationNumber(String registrationNo){
        final int[] temp = new int[1];
    	temp[0] = -1;
    	map.forEach((id, carData) -> {
			if(carData.getRegistrationNo().equals(registrationNo)){
				System.out.println(id+" ");
				temp[0]=id;
			}	
		});	
		if(temp[0]==-1){
			return false;
		}
		return true;
    }
	public static void main(String[] args){
	    ParkMyCar po = new ParkMyCar();
		    for(int i=0;i<50;i++){
		    	 System.out.print("$ ");
			     Scanner sc = new Scanner(System.in);
			     String str = sc.nextLine();
			     String[] splitStr = str.split("\\s+");
			     switch(splitStr[0]){
			     	case "create_parking_lot":
			     	  	totalSlots = Integer.parseInt(splitStr[1]);
			     	  	System.out.println("Created a parking lot with "+totalSlots+" slots");
			     	  	break;
			   		case "park":
			   			int allocatedSlots = po.totalAllocatedSlots();
			   			if(allocatedSlots>=totalSlots){
			   				int temp = po.addIfSlotIsPresent(splitStr[1],splitStr[2]);
			   				if(temp==0){
								System.out.println("Sorry, parking slot is full");
							} else{
								System.out.println("Allocated slot number:"+temp);
							}
						}
			   			else if(allocatedSlots<totalSlots){
			   				int allocatedSlot = po.parkCar(splitStr[1],splitStr[2]);
							System.out.println("Allocated slot number:"+allocatedSlot);
						}
						break;
					case "leave":
						int slotNo = Integer.parseInt(splitStr[1]); 
						po.removeSlot(slotNo);
						System.out.println("Slot number "+slotNo+" is free");
						break;
					case "status":
						po.printStatus();
						break;
					case "registration_numbers_for_cars_with_colour":
						po.findRegistrationNoByColour(splitStr[1]);
						break;
					case "slot_numbers_for_cars_with_colour":
						po.findIdByColour(splitStr[1]);
						break;
					case "slot_number_for_registration_number":
						boolean isExist = po.findSlotNoByRegistrationNumber(splitStr[1]);
						if(!isExist){
							System.out.println("Not found");
						}
						break;
					case "exit":
						return;
					}
				}
		}
}
class Park{
	public int slotNo;
    public String registrationNo;
    public String colour;
    public Park(int slotNo, String registrationNo, String colour)
    {
        this.slotNo = slotNo;
        this.registrationNo = registrationNo;
        this.colour = colour;
    }    

    String getRegistrationNo(){
    	return registrationNo;
    }

    String getColour(){
    	return colour;
    }

    int getSlotNo(){
    	return slotNo;
    }
}