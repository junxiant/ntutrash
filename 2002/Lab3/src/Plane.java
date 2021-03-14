
public class Plane {

	private static PlaneSeat[] seat = new PlaneSeat[12];
	private static int numEmptySeat = 12;
	
	public Plane() {
		for(int i=0;i<seat.length;i++) {
			seat[i]=new PlaneSeat(i+1);
		}
	}

	private PlaneSeat[] sortSeats() {
		
		PlaneSeat seat2[] = new PlaneSeat[12];
		for(int i = 0; i< seat2.length; i++) {
			seat2[i]=seat[i];
		}
		
		for(int i = 0; i< seat2.length; i++) {
			for(int j = i+1; j< seat2.length; j++) {
				if(seat2[i].getCustomerID() > seat2[j].getCustomerID())
				{
					PlaneSeat temp = new PlaneSeat();
					
					temp = seat2[i];
					seat2[i]= seat2[j];
					seat2[j]= temp;
				}
			}
		}
		return seat2;

	}

	public void showNumEmptySeats() {
		System.out.println("There are " +numEmptySeat+ " empty seats");
	}

	public void showEmptySeats() {
		System.out.println("The following seats are empty:");
		
		for(int i=0; i<seat.length;i++) {
			if(seat[i].isOccupied()==false) {
				System.out.println("SeatID " + seat[i].getSeatID());
			}
		}
	}

	public void showAssignedSeats(boolean bySeatId)
	{
		PlaneSeat temp[] = sortSeats();
		
		System.out.println("The seat assignments are as follow:");
		for(int i=0; i<seat.length;i++) {
			if(seat[i].isOccupied()==true && bySeatId)
			{
				System.out.println("SeatID " + seat[i].getSeatID()+ " is assigned to CustomerID " +seat[i].getCustomerID()+ ".");
			}
			else if(temp[i].isOccupied()==true && bySeatId==false)
			{
				System.out.println("SeatID " + temp[i].getSeatID()+ " is assigned to CustomerID " + temp[i].getCustomerID()+ ".");
			}
		}
	}

	public void assignSeat(int seatId, int cust_id) {
		
		if(seat[seatId-1].isOccupied()==false) {
			seat[seatId-1].assigned(cust_id);
			System.out.println("Seat Assigned!");
			numEmptySeat--;
		}
		else {
			System.out.println("Seat already assigned to a customer.");
		}
	}

	public void unAssignSeat(int seatId) {
		seat[seatId-1].unAssign();
		System.out.println("Seat Unassigned!");
		numEmptySeat++;
	}

}
