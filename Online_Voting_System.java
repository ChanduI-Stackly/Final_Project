package Final_Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Online_Voting_System{
	
	// This class stores student login details
	static class User{
		String username;
		String password;
		boolean hasVoted;
		
		User(String username, String password){
			this.username = username;
			this.password = password;
			this.hasVoted = false;
		}
	}
	
	// This class stores candidate details
	static class Candidate{
		int id;
		String name;
		int voteCount;
		
		Candidate(int id, String name){
			this.id = id;
			this.name = name;
			this.voteCount = 0;
		}
	}
	
	// Scanner is used to take input from the user
	static Scanner scanner = new Scanner(System.in);
	
	// Stores all registered students
	static Map<String, User> users = new HashMap<>();
	
	// Stores all candidates
	static Map<Integer, Candidate> candidates = new HashMap<>();
	
	public static void main(String[] args) {
		
		// Adding candidates
		candidates.put(1, new Candidate(1, "Chandu"));
		candidates.put(2, new Candidate(2, "Sony"));
		candidates.put(3, new Candidate(3, "Kushi"));
		
		System.out.println("\n-------ONLINE VOTING SYSTEM--------");
		
		while (true) {
			
			System.out.println("\n1. Register Student");
			System.out.println("2. Login and Vote");
			System.out.println("3. View Total Votes");
			System.out.println("4. View Election result");
			System.out.println("5. Exit");
			
			System.out.print("\nEnter your choice: ");
			
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				
				switch (choice) {
				case 1:
					registerStudent();
					break;
					
				case 2:
					loginAndVote();
					break;
					
				case 3:
					displayTotalVotes();
					break;	
					
				case 4:
					displayResult();
					break;
					
				case 5:
					System.out.println("\nThank you for using Online Voting System.");
					scanner.close();
					return;

				default:
					System.out.println("\nPlease enter a valid choice");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("\nInvalid input. Please enter numbers only.");
				scanner.nextLine();
			}
		}
	}
	
	// Method to register a student
	static void registerStudent() {
		
		System.out.println("\n----- STUDENT REGISTRATION -----");
		
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		
		// Check whether username already exixts
		if (users.containsKey(username)) {
			System.out.println("Username already exists.");
			return;
		}
		
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		
		// Create a new User object
		User newUser = new User(username, password);
		
		// Store the user in HashMap
		users.put(username, newUser);
		
		System.out.println("Registration successful.");
	}
	
	// Method for login and voting
	static void loginAndVote() {
		
		System.out.println("\n------ STUDENT LOGIN ------");
		
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		
		// Check whether student is registered
		if (!users.containsKey(username)) {
			System.out.println("Student not registered.");
			return;
		}
		
		// Get student details
		User user = users.get(username);
		
		// Check password
		if (!user.password.equals(password)) {
			System.out.println("Incorrect password.");
			return;
		}
		
		// Check whether student already voteed
		if (user.hasVoted) {
			System.out.println("You have already voted.");
			return;
		}
		
		System.out.println("\n------CANDIDATE LIST -------");
		
		// Display all candidates
		for (Candidate candidate : candidates.values()) {
			
			System.out.println(candidate.id + " . " + candidate.name);
		}
		
		System.out.print("\nEnter candidate number: ");
		
		try {
			int candidateId = scanner.nextInt();
			scanner.nextLine();
			
			// Check candidate ID
			if (!candidates.containsKey(candidateId)) {
				System.out.println("Invalid candidate number.");
				return;
			}
			
			// Get selected candidate
			Candidate selectedCandidate = candidates.get(candidateId);
			
			// Increase vote count
			selectedCandidate.voteCount++;
			
			// Mark student as voted
			user.hasVoted = true;
			
			System.out.println("\nYour vote has been recorded successfully.");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("Please enter a valid candidate number.");
			scanner.nextLine();
		}
 	}
	
	// Method to display total votes
	static void displayTotalVotes() {
		
		System.out.println("\n----- TOTAL VOTES ------");
		
		for (Candidate candidate : candidates.values()) {
			
			System.out.println(candidate.name + " : " + candidate.voteCount + "votes");
		}
	}
	
	// Method to display election result
	static void displayResult() {
		
		Candidate finalist = null;
		Candidate runnerUp = null;
		
		// Find finalist and runnerup
		for (Candidate candidate : candidates.values()) {
			
			if (finalist == null || candidate.voteCount > finalist.voteCount) {
				
				runnerUp = finalist;
				finalist = candidate;
				
			} else if (runnerUp == null || candidate.voteCount > runnerUp.voteCount) {
				
				runnerUp = candidate;
				
			} 
		}
			
			System.out.println("\n------ ELECTION RESULT -----");
			
			if (finalist != null) {
				
				System.out.println("Finalist :" + finalist.name + " - " + finalist.voteCount + "votes");
			}
			
			if (runnerUp != null) {
				
				System.out.println("Runner-Up :" + runnerUp.name + " - " + runnerUp.voteCount + "votes");
			}
		}
	}
