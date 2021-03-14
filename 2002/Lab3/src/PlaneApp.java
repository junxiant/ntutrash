import java.util.Scanner;

public class PlaneApp {

	public PlaneApp() {

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int userinput;
		boolean loop = true;
		Plane colossusplane = new Plane();
		
		System.out.println("(1)Show number of empty seats"+
				"\n(2)Show the list of empty seats"
				+ "\n(3)Show the list of seat assignments by seat ID"
				+ "\n(4)Show the list of seat assignments by customer ID"
				+ "\n(5)Assign a customer to a seat"
				+ "\n(6)Remove a seat assignment"
				+ "\n(7)Exit");
		
		while(loop) {
			
			System.out.println("\nEnter the number of your choice:");
			userinput = sc.nextInt();
			
			switch(userinput){
			case 1:
				colossusplane.showNumEmptySeats();
				break;
			case 2:
				colossusplane.showEmptySeats();
				break;
			case 3:
				colossusplane.showAssignedSeats(true);
				break;
			case 4:
				colossusplane.showAssignedSeats(false);
				break;
			case 5:
				System.out.println("Assigning Seat ..");
				System.out.println("Please enter Seat ID:");
				int seatId = sc.nextInt();
				System.out.println("Please enter Customer ID:");
				int cust_id = sc.nextInt();
				colossusplane.assignSeat(seatId, cust_id);
				break;
			case 6:
				System.out.println("Enter SeatID to unassign customer from:");
				int seatNumToUnassign = sc.nextInt();
				colossusplane.unAssignSeat(seatNumToUnassign);
				break;
			case 7:
				loop=false;
				break;
			default:
					
			}
			
			
		}

	}

}
