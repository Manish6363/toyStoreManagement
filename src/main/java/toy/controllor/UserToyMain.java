package toy.controllor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import toy.dao.AdminCRUD;
import toy.dao.ToyCRUD;
import toy.dao.UserCRUD;
import toy.dto.Admin;
import toy.dto.Toy;
import toy.dto.User;

public class UserToyMain {
	static Scanner scan = new Scanner(System.in);

	static AdminCRUD adCrud = new AdminCRUD();

	static ToyCRUD toyCrud = new ToyCRUD();

	static UserCRUD userCrud = new UserCRUD();

	static ArrayList<Toy> al = new ArrayList<Toy>();

	public static void adminInsert() {
		System.out.println("\t\t.............................................");
		System.out.println("\t\t====WELCOME TO ADMIN REGISTRATION SECTION====");
		System.out.println("\t\t`````````````````````````````````````````````");
		System.out.print(
				"\t\tAdmin ID should be followed by maximum 4-char of Shop name with unique number.\n\t\tEnter Admin Id: ");
		String id = scan.nextLine();
		System.out.print("\t\tEnter Admin Name: ");
		String name = scan.nextLine();
		System.out.print("\t\tEnter Admin Shop-name: ");
		String shopName = scan.nextLine();
		System.out.print("\t\tEnter Admin Email: ");
		String email = scan.nextLine();
		System.out.print("\t\tEnter Admin Phone: ");
		long phone = scan.nextLong();
		scan.nextLine();
		System.out.print("\t\tEnter Admin Address: ");
		String address = scan.nextLine();
		System.out.print("\t\tEnter Admin Password: ");
		String pwd = scan.nextLine();

		Admin admin = new Admin(id, name, shopName, email, phone, address, pwd);

		try {
			int data = adCrud.insertAdminData(admin);
			System.out.println("\n\t\t" + data + "-new row of Admin is Registered...!");
			System.out.println("\t\tTHANK YOU for Registration...!\n");
		} catch (SQLException e) {
			System.out.println("\nSomething Wrong. Try Again...!");
			adminInsert();
//			e.printStackTrace();
		}
	}

	public static Admin adminLoginMain() {
		System.out.println("\n\t\t......................................");
		System.out.println("\t\t====WELCOME TO ADMIN LOGIN SECTION====");
		System.out.println("\t\t``````````````````````````````````````");
		System.out.print("\t\tEnter Email: ");
		String email = scan.nextLine();
		System.out.print("\t\tEnter Password: ");
		String pwd = scan.nextLine();
		Admin admin = null;
		try {
			if (adCrud.adminLogin(email) != admin) {
				admin = adCrud.adminLogin(email);
				if (admin.getPassword().equals(pwd)) {
					return admin;
				} else {
					System.out.println("\n\t\tInvalid Password. Try Again...!\n");
					return adminLoginMain();
				}
			} else {
				System.out.println("\n\t\tInvalid Email Id. Try Again...!\n");
				return adminLoginMain();
			}
		} catch (SQLException e) {
			System.out.println("Something Wrong, Please Try Again...!\n");
//			e.printStackTrace();
		}
		return admin;
	}

	public static void update(Admin admin) {
		System.out.println(
				"\t\t\t\tEnter your choice to update: \n\t\t\t\t\t1.NAME    2.SHOP NAME    3.EMAIL    4.PHONE    5.ADDRESS    6.PASSWORD");
		int ch = scan.nextInt();
		int n = 0;
		try {
			if (ch == 1) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW NAME: ");
				String name = scan.nextLine();
				n = adCrud.updateAdminData(admin, ch, name);
				System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");

			} else if (ch == 2) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW SHOP NAME: ");
				String shName = scan.nextLine();
				n = adCrud.updateAdminData(admin, ch, shName);
				System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");

			} else if (ch == 3) {
				scan.nextLine();
				String stEmail = null;
				do {
					System.out.print("\t\t\t\tEnter NEW EMAIL: ");
					String email = scan.nextLine();
					try {
						n = adCrud.updateAdminData(admin, ch, email);
						System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");
						stEmail = "no";
					} catch (SQLException e) {
						System.out.println("\n\t\t\t\tMr." + admin.getName()
								+ ", this email is already taken by someone. SORRY, Try with another...!\n");
						stEmail = "yes";
					}
				} while (stEmail.equals("yes"));

			} else if (ch == 4) {
				System.out.print("\t\t\t\tEnter NEW PHONE No.: ");
				long ph = scan.nextLong();
				n = adCrud.updateAdminData(admin, ch, ph);
				System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");

			} else if (ch == 5) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW ADDRESS: ");
				String ad = scan.nextLine();
				n = adCrud.updateAdminData(admin, ch, ad);
				System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");

			} else if (ch == 6) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW PASSWORD: ");
				String pwd = scan.nextLine();
				n = adCrud.updateAdminData(admin, ch, pwd);
				System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");

			} else {
				System.out.println(
						"\n\t\t\t\tMr." + admin.getName() + ", given Update Option is Incorrect, SORRY try again...!");
				update(admin);
			}
		} catch (SQLException e) {
			System.out.println("\n\t\t\t\t" + n + "-row of Mr." + admin.getName() + " has Updated...!\n");
			e.printStackTrace();
		}
		System.out.println();
	}

	public static void toyInsert(String id) {
		ArrayList<Toy> al = new ArrayList<Toy>();
		String ch = null;
		do {
			System.out.print("\t\t\t\tEnter Toy Name: ");
			String name = scan.nextLine();
			System.out.print("\t\t\t\tEnter Toy color: ");
			String color = scan.nextLine();
			System.out.print("\t\t\t\tEnter Toy Price: ");
			double price = scan.nextDouble();
			System.out.print("\t\t\t\tEnter Toy Quantity: ");
			int quantity = scan.nextInt();
			Toy toy = new Toy(name, color, price, quantity);
			al.add(toy);
			System.out.print("\tDo you want to add more toys details(yes/no)?: ");
			ch = scan.next().toLowerCase();
			scan.nextLine();
		} while (ch.equals("yes"));
		try {
			int data = toyCrud.insertToyData(al, id);
			System.out.println("\n\t\t\t\t" + data + "-new row of Toy is Uploaded...!");
			System.out.println("\t\t\t\tTHANK YOU for uploading " + data + "-Toy Details...!\n");
		} catch (SQLException e) {
			System.out.println("\nSomething went Wrong. Try Again...!");
			toyInsert(id);
//			e.printStackTrace();
		}
	}

	public static void updateToy() {
		System.out.println(
				"\t\t\t\tEnter your choice to update: \n\t\t\t\t\t1.TOY NAME    2.TOY COLOR    3.PRICE    4.QUANTITY");
		int ch = scan.nextInt();
		System.out.print("\t\t\t\tEnter Toy ID: ");
		int id = scan.nextInt();
		int n = 0;
		try {
			if (ch == 1) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW TOY NAME: ");
				String name = scan.nextLine();
				n = toyCrud.updateToyData(id, ch, name);
				System.out.println("\n\t\t\t\t" + n + "-row of Toy is Updated...!\n");

			} else if (ch == 2) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW TOY COLOR: ");
				String color = scan.nextLine();
				n = toyCrud.updateToyData(id, ch, color);
				System.out.println("\n\t\t\t\t" + n + "-row of Toy is Updated...!\n");

			} else if (ch == 3) {
				System.out.print("\t\t\t\tEnter NEW TOY PRICE: ");
				double price = scan.nextDouble();
				n = toyCrud.updateToyData(id, ch, price);
				System.out.println("\n\t\t\t\t" + n + "-row of Toy is Updated...!\n");

			} else if (ch == 4) {
				System.out.print("\t\t\t\tEnter NEW TOY QUANTITY: ");
				int quan = scan.nextInt();
				n = toyCrud.updateToyData(id, ch, quan);
				System.out.println("\n\t\t\t\t" + n + "-row of Toy is Updated...!\n");

			} else {
				System.out.println("\n\t\t\t\tIncorrect Update Option. Try Again...!\n");
				updateToy();
			}
		} catch (SQLException e) {
			System.out.println("\n\t\t\t\tGiven Record not found to Update...!");
			e.printStackTrace();
		}
		System.out.println();
	}

	public static void userInsert() {
		System.out.println("\t\t.............................................");
		System.out.println("\t\t====WELCOME TO USER REGISTRATION SECTION====");
		System.out.println("\t\t`````````````````````````````````````````````");
		System.out.print("\t\tEnter User Id: ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.print("\t\tEnter User Name: ");
		String name = scan.nextLine();
		System.out.print("\t\tEnter User Email: ");
		String email = scan.nextLine();
		System.out.print("\t\tEnter User Phone: ");
		long phone = scan.nextLong();
		System.out.print("\t\tEnter User Age: ");
		int age = scan.nextInt();
		scan.nextLine();
		System.out.print("\t\tEnter User Address: ");
		String address = scan.nextLine();
		System.out.print("\t\tEnter User Wallet: ");
		double wallet = scan.nextDouble();
		scan.nextLine();
		System.out.print("\t\tEnter User Password: ");
		String pwd = scan.nextLine();

		User user = new User(id, name, email, phone, age, address, wallet, pwd);

		try {
			int data = userCrud.insertUserData(user);
			System.out.println("\n\t\t" + data + "-new User is Registered...!");
			System.out.println("\t\tTHANK YOU Mr. " + name + ", for Registration...!\n");
		} catch (SQLException e) {
			System.out.println("\nSomething Wrong. Try Again...!");
			userInsert();
//			e.printStackTrace();
		}
	}

	public static User userLoginMain() {
		System.out.println("\t\t.....................................");
		System.out.println("\t\t====WELCOME TO USER LOGIN SECTION====");
		System.out.println("\t\t`````````````````````````````````````");
		System.out.print("\t\tEnter Email: ");
		String email = scan.nextLine();
		System.out.print("\t\tEnter Password: ");
		String pwd = scan.nextLine();
		User user = null;
		try {
			if (userCrud.userLogin(email) != user) {
				user = userCrud.userLogin(email);
				if (user.getPassword().equals(pwd)) {
					return user;
				} else {
					System.out.println("Invalid Password. Try Again...!");
					return userLoginMain();
				}
			} else {
				System.out.println("Invalid Email Id. Try Again...!");
				return userLoginMain();
			}
		} catch (SQLException e) {
			System.out.println("Something Wrong...!");
//			e.printStackTrace();
		}
		return user;
	}

	public static void updateUser(User user) {
		System.out.println(
				"\t\t\t\tEnter your choice to update: \n\t\t\t\t\t1.NAME    2.EMAIL    3.PHONE    4.AGE    5.ADDRESS    6.PASSWORD");
		int ch = scan.nextInt();
		int n = 0;
		try {
			if (ch == 1) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW NAME: ");
				String name = scan.nextLine();
				n = userCrud.updateUserData(user, ch, name);
				System.out.println("\n\t\t\t\tMr. " + user.getName() + ", " + n + "-row of is Updated...!\n");

			} else if (ch == 2) {
				scan.nextLine();
				String stEmail = null;
				do {
					System.out.print("\t\t\t\tEnter NEW EMAIL: ");
					String email = scan.nextLine();
					try {
						n = userCrud.updateUserData(user, ch, email);
						System.out.println("\n\t\t\t\tMr. " + user.getName() + ", " + n + "-row of is Updated...!\n");
						stEmail = "no";
					} catch (SQLException e) {
						System.out.println("\n\t\t\t\tMr." + user.getName()
								+ ", this email is already taken by someone. SORRY, Try with another...!\n");
						stEmail = "yes";
					}
				} while (stEmail.equals("yes"));
				System.out.println();

			} else if (ch == 3) {
				System.out.print("\t\t\t\tEnter NEW PHONE No.: ");
				long ph = scan.nextLong();
				n = userCrud.updateUserData(user, ch, ph);
				System.out.println("\n\t\t\t\tMr. " + user.getName() + ", " + n + "-row of is Updated...!\n");

			} else if (ch == 4) {
				System.out.print("\t\t\t\tEnter NEW AGE: ");
				int age = scan.nextInt();
				n = userCrud.updateUserData(user, ch, age);
				System.out.println("\n\t\t\t\tMr. " + user.getName() + ", " + n + "-row of is Updated...!\n");

			} else if (ch == 5) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW ADDRESS: ");
				String ad = scan.nextLine();
				n = userCrud.updateUserData(user, ch, ad);
				System.out.println("\n\t\t\t\tMr. " + user.getName() + ", " + n + "-row of is Updated...!\n");

			} else if (ch == 6) {
				scan.nextLine();
				System.out.print("\t\t\t\tEnter NEW PASSWORD: ");
				String pwd = scan.nextLine();
				n = userCrud.updateUserData(user, ch, pwd);
				System.out.println("\n\t\t\t\tMr. " + user.getName() + ", " + n + "-row of is Updated...!\n");

			} else {
				System.out.println("\n\t\t\t\tMr. " + user.getName() + ", Update Option is Incorrect...!");
				updateUser(user);
			}
		} catch (SQLException e) {
			System.out.println("\n\t\t\t\tGiven Record not found to Update...!");
			e.printStackTrace();
		}
		System.out.println();
	}

	public static void addToCart() {
		System.out.print("\t\t\t\tEnter the Toy ID to purchase: ");
		int id = scan.nextInt();
		System.out.print("\t\t\t\tEnter the Quantity of Toy: ");
		int quan = scan.nextInt();
		Toy toy = null;
		try {
			toy = toyCrud.purchageToys(id, quan);
			if (toy != null) {
				al.add(toy);
			}
		} catch (SQLException e) {
			System.out.println("Something went Wrong...!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		boolean key = true;
		do {
			System.out.println(".................................................");
			System.out.println("==== WELCOME TO TOY STORE MANAGEMENT PROJECT ====");
			System.out.println("`````````````````````````````````````````````````");

			System.out.println("Enter your choice: \n\t1.Admin \n\t2.User \n\t3.Exit");
			int choice = scan.nextInt();
			switch (choice) {
			case 1: {
				boolean flag = true;
				do {
					System.out.println("\t................................");
					System.out.println("\t====WELCOME TO ADMIN SECTION====");
					System.out.println("\t````````````````````````````````");

					System.out.println(
							"\tEnter your choice: \n\t\t1.Register \n\t\t2.Login \n\t\t3.Exit from Admin Section");
					int val = scan.nextInt();
					switch (val) {
					case 1: {
						scan.nextLine();
						adminInsert();
					}
						break;

					case 2: {
						scan.nextLine();
						Admin admin = adminLoginMain();
						if (admin != null) {
							boolean adminCh = true;
							do {
								System.out.println("\n\t\t\t......................................................");
								System.out.println(
										"\t\t\t==== Mr." + admin.getName() + " HAS LOGGED-IN SUCCESSFULLY====");
								System.out.println("\t\t\t```````````````````````````````````````````````````````");

								System.out
										.println("\t\t\tMr." + admin.getName() + ", enter your choice: \n\t\t\t\t1.Mr. "
												+ admin.getName() + " Profile Section\n\t\t\t\t2.Toy's Details at "
												+ admin.getShopName() + "\n\t\t\t\t3.Logout Mr." + admin.getName());
								int ch = scan.nextInt();
								switch (ch) {
								case 1: {
									boolean userChPr = true;
									do {
										System.out.println(
												"\t\t\t\t.......................................................");
										System.out.println("\t\t\t\t====WELCOME TO Mr." + admin.getName()
												+ " PROFILE SECTION====");
										System.out.println(
												"\t\t\t\t``````````````````````````````````````````````````````");

										System.out.println(
												"\t\t\t\tEnter your choice: \n\t\t\t\t\t1.Fetch Profile \n\t\t\t\t\t2.Update Profile \n\t\t\t\t\t3.Delete Profile \n\t\t\t\t\t4.Exit from Profile Section");
										int chPr = scan.nextInt();
										switch (chPr) {
										case 1: {
											adCrud.fetchAdminProfile(admin);
										}
											break;

										case 2: {
											update(admin);
										}
											break;

										case 3: {
											try {
												int d = adCrud.deleteAdminData(admin.getId());
												System.out.println("\t\t\t" + d + "-row of Admin is Deleted...!\n");
												userChPr = false;
												adminCh = false;
//												flag = false;
											} catch (SQLException e) {
												System.out.println("\n\tSomething went Wrong...!\n");
//												e.printStackTrace();
											}
										}
											break;

										case 4: {
											System.out.println("\n\t\t\t\tTHANK YOU! Mr. " + admin.getName()
													+ ", for Visiting your PROFILE Section...!");
											System.out.println("\t\t\t\tExisted from Mr. " + admin.getName()
													+ ", PROFILE-Section...!\n");
											userChPr = false;
										}
											break;
										default: {
											System.out.println(
													"Mr. " + admin.getName() + ", given Input is Incorrect...!");
											break;
										}
										}
									} while (userChPr);
								}
									break;

								case 2: {
									boolean adminChToy = true;
									do {
										System.out
												.println("\t\t\t\t..................................................");
										System.out.println(
												"\t\t\t\t====WELCOME TO " + admin.getShopName() + " TOY's SECTION====");
										System.out
												.println("\t\t\t\t``````````````````````````````````````````````````");
										System.out.println(
												"\t\t\t\tEnter your choice: \n\t\t\t\t\t1.Upload New Toy Information \n\t\t\t\t\t2.Fetch all Toys of your shop \n\t\t\t\t\t3.Update Toy Info \n\t\t\t\t\t4.Delete Toy Info \n\t\t\t\t\t5.Exit from Toy Section");
										int chToy = scan.nextInt();
										switch (chToy) {

										case 1: {
											scan.nextLine();
											toyInsert(admin.getId());
										}
											break;

										case 2: {
											toyCrud.fetchToy(admin.getId());
										}
											break;

										case 3: {
											updateToy();
										}
											break;

										case 4: {
											try {
												System.out.print("\t\t\t\tEnter Toy ID: ");
												int id = scan.nextInt();
												int d = toyCrud.deleteToyData(id);
												System.out.println("\t\t\t\t" + d + "-row of Toy is Deleted...!\n");
											} catch (SQLException e) {
												System.out.println("\n\tSomething went Wrong...!\n");
//												e.printStackTrace();
											}
										}
											break;

										case 5: {
											System.out.println("\n\t\t\t\tTHANK YOU Mr. " + admin.getName()
													+ ", for Visiting at " + admin.getShopName() + " TOY Section...!");
											System.out.println("\t\t\t\tExisted from  " + admin.getShopName()
													+ " TOY Section...!\n");
											adminChToy = false;
										}
											break;
										default: {
											System.out.println("Incorrect Input...!");
											break;
										}
										}
									} while (adminChToy);
								}
									break;

								case 3: {
									System.out.println("\n\t\t\tTHANK YOU Mr. " + admin.getName()
											+ ", for your Log-in Session...!");
									System.out.println(
											"\t\t\tMr. " + admin.getName() + ", has logged-out successfully...!\n");
									adminCh = false;
								}
									break;
								default: {
									System.out.println("Incorrect Input...!");
									break;
								}
								}
							} while (adminCh);
						}
					}
						break;

					case 3: {
						System.out.println("\n\tTHANK YOU for Visiting ADMIN Section...!");
						System.out.println("\tExisted from ADMIN Section.\n");
						flag = false;
					}
						break;
					default: {
						System.out.println("Incorrect Input...!");
						break;
					}
					}
				} while (flag);
			}
				break;

			case 2: {
				boolean flag = true;
				do {
					System.out.println("\t...............................");
					System.out.println("\t====WELCOME TO USER SECTION====");
					System.out.println("\t```````````````````````````````");

					System.out.println(
							"\tEnter your choice: \n\t\t1.New User Registration \n\t\t2.User Login \n\t\t3.Exit from User Section");
					int val = scan.nextInt();
					switch (val) {
					case 1: {
						scan.nextLine();
						userInsert();
					}
						break;

					case 2: {
						scan.nextLine();
						User user = userLoginMain();
						if (user != null) {
							boolean userCh = true;
							do {
								System.out.println("\n\t\t\t......................................................");
								System.out.println(
										"\t\t\t====WELCOME YOU Mr. " + user.getName() + ", at LOGIN SECTION====");
								System.out.println("\t\t\t````````````````````````````````````````````````````````");

								System.out.println("\t\t\tMr. " + user.getName()
										+ ", Enter your choice: \n\t\t\t\t1.Mr. " + user.getName()
										+ " Profile \n\t\t\t\t2.Toy's Stores \n\t\t\t\t3.Logout Mr. " + user.getName());
								int ch = scan.nextInt();
								switch (ch) {
								case 1: {
									boolean userChPr = true;
									do {
										System.out.println(
												"\t\t\t\t.....................................................");
										System.out.println("\t\t\t\t====WELCOME TO Mr. " + user.getName()
												+ " PROFILE SECTION====");
										System.out.println(
												"\t\t\t\t`````````````````````````````````````````````````````");

										System.out.println("\t\t\t\tMr. " + user.getName()
												+ ", Enter your choice: \n\t\t\t\t\t1.Fetch Profile \n\t\t\t\t\t2.Update Profile \n\t\t\t\t\t3.Delete Profile \n\t\t\t\t\t4.Exit from Profile Section");
										int chPr = scan.nextInt();
										switch (chPr) {
										case 1: {
											userCrud.fetchUserProfile(user);
										}
											break;

										case 2: {
											updateUser(user);
										}
											break;

										case 3: {
											try {
												int d = userCrud.deleteUserData(user.getId());
												System.out.println("\t\t\t\t" + d + "-row of User is Deleted...!\n");
												userChPr = false;
												userCh = false;
//												flag=false;
											} catch (SQLException e) {
												System.out.println("\n\tSomething went Wrong...!\n");
//												e.printStackTrace();
											}
										}
											break;

										case 4: {
											System.out.println("\n\t\t\t\tTHANK YOU Mr. " + user.getName()
													+ " for Visiting your PROFILE Section...!");
											System.out.println("\t\t\t\tMr. " + user.getName()
													+ ", you are Existing from your PROFILE Section...!\n");
											userChPr = false;
										}
											break;
										default: {
											System.out.println("Mr. " + user.getName() + "Incorrect Input...!");
											break;
										}
										}
									} while (userChPr);
								}
									break;

								case 2: {
									System.out.println("\t\t\t\t.......................................");
									System.out.println("\t\t\t\t====WELCOME TO TOY's STORES SECTION====");
									System.out.println("\t\t\t\t```````````````````````````````````````");
									boolean userChToy = true;
									double grandBill = 0.0;
									do {
										System.out.println(
												"\t\t\t\tEnter your choice: \n\t\t\t\t\t1.Display All Toy Information \n\t\t\t\t\t2.Add to your Cart \n\t\t\t\t\t3.Purchase Your Cart-items \n\t\t\t\t\t4.View your Cart-items \n\t\t\t\t\t5.Exit from Toy Store");
										int chToy = scan.nextInt();
										double totalBill = 0.0;
										switch (chToy) {
										case 1: {
											toyCrud.fetchAllToy();
										}
											break;

										case 2: {
											toyCrud.fetchAllToy();
											String str = "yes";
											do {
												addToCart();
												System.out.print("\t\t\t\tMr." + user.getName()
														+ ",do you want to more purchase(yes/no)?: ");
												str = scan.next().toLowerCase();
											} while (str.equals("yes"));
											if (al.size() != 0) {
												System.out
														.println("\n\t\t\t\tMr. " + user.getName() + ", Your cart is:");
												System.out.println("\t\t\t\t\t |-----------------------------------|");
												System.out.println("\t\t\t\t\t | NAME    COLOUR   QUANTITY   PRICE |");
												System.out.println("\t\t\t\t\t |-----------------------------------|");
												for (Toy t : al) {
													double total = 0.0;
													total = total + (t.getQuantity() * t.getPrice());
													totalBill += total;
													System.out.println("\t\t\t\t\t | " + t.gettName() + "   "
															+ t.getColor() + "       " + t.getQuantity() + "          "
															+ total + " |");
												}
												System.out.println("\t\t\t\t\t -------------------------------------");
												System.out.println(
														"\t\t\t\t\t                  GRAND TOTAL=" + totalBill);
												grandBill = totalBill;
											} else {
												System.out.println("\n \t\t\t\tMr. " + user.getName()
														+ ", Your Cart is EMPTY. \n\t\t\t\tYou have not added any items to your Cart...!\n");
											}
											System.out.println();
										}
											break;

										case 3: {
											if (al.size() != 0) {
												System.out.println("\n\t\t\t\tMr." + user.getName()
														+ ", your Wallet Balance is: " + user.getWallet());
												System.out.print("\t\t\t\tMr." + user.getName()
														+ " want to make Payment(yes/no): ");
												String st = scan.next().toLowerCase();
												System.out.println();

												if (st.equals("no")) {
													System.out.println("\n\t\t\t\t SORRY Mr. " + user.getName()
															+ ", the payment is failed, so your order is not purchased...!");
												}

												while (st.toLowerCase().equals("yes")) {
													if (user.getWallet() >= grandBill) {
														double amount = user.getWallet() - grandBill;
														double currBal=0.0;
														System.out.println("\n\tThank you Mr." + user.getName()
																+ ", payment is successfull...!");
														try {
															currBal = userCrud.updateWallet(user.getId(),
																	amount);
															for (Toy t : al) {
																toyCrud.updateToyQuantity(t.getId(), t.getQuantity());
															}
														} catch (SQLException e) {
															System.out.println("Something went wrong after payment..!");
															e.printStackTrace();
														}
														st = "no";
														System.out.println();
														System.out.println("\t\t\tMr. " + user.getName()
																+ ", Your Product Bill is:");
														System.out.println(
																"\t\t\t\t\t |-----------------------------------|");
														System.out.println(
																"\t\t\t\t\t | NAME    COLOUR   QUANTITY   PRICE |");
														System.out.println(
																"\t\t\t\t\t |-----------------------------------|");
														double bill = 0.0;
														for (Toy t : al) {
															double tB = 0.0;
															tB = tB + (t.getQuantity() * t.getPrice());
															bill += tB;
															System.out.println("\t\t\t\t\t | " + t.gettName() + "   "
																	+ t.getColor() + "       " + t.getQuantity()
																	+ "          " + tB + " |");
														}
														System.out.println(
																"\t\t\t\t\t -------------------------------------");
														System.out.println(
																"\t\t\t\t\t             Total Paid Amount=" + bill);
														System.out.println();
														System.out.println(
																"\t\t\t\t\tTHANK YOU for Purchasing, Please Visit Again...!");
														System.out.println("\t\t\tMr. " + user.getName()
														+ ", your Cuurent Wallet Balance: " + currBal);
														System.out.println();
														al.clear();
													} else {
														System.out.println("\t\t\t\tSORRY Mr. " + user.getName()
																+ ", payment is failed due to insufficient balance in your wallet. \n\t\t\tPlease add more money to your wallet....!");
														System.out.print("\t\t\t\tDo you want to add money(yes/no): ");
														String addMoney = scan.next().toLowerCase();
														if (addMoney.equals("yes")) {
															System.out
																	.print("\n\t\t\t\tHow much amount want to add?: ");
															double money = scan.nextDouble();
															try {
																user = userCrud.addWalletMoney(user.getId(),
																		money + user.getWallet());
																System.out.println("\n\t\t\t\tMr." + user.getName()
																		+ " " + money
																		+ " is added to your Wallet. Thank You....!\n");
																System.out.println("\t\t\t\tMr." + user.getName()
																+ ", your Wallet Balance is: " + user.getWallet());
															} catch (SQLException e) {
																System.out.println(
																		"Something went wromg during adding of money....!");
//															e.printStackTrace();
															}
															System.out.print("\t\t\t\tMr." + user.getName()
																	+ " want to make Payment(yes/no): ");
															st = scan.next();
														} else {
															st = "no";
															System.out.println("\n\t\t\t\tSORRY Mr. " + user.getName()
																	+ ",you cannot able to make your payment due to insufficient wallet balance....!\n");
															System.out.println("\n\t\t\t\tMr." + user.getName()
															+ ", your Wallet Balance is: " + user.getWallet());
														}
													}
												}
											} else {
												System.out.println("\n\t\t\t\tSORRY Mr. " + user.getName()
														+ ", Your cart is EMPTY...!\n");
											}
										}
											break;

										case 4: {
											if (al.size() != 0) {
												System.out.println("\n\t\t\t\tMr." + user.getName() + ",Your cart is:");
												System.out.println("\t\t\t\t\t |-----------------------------------|");
												System.out.println("\t\t\t\t\t | NAME    COLOUR   QUANTITY   PRICE |");
												System.out.println("\t\t\t\t\t |-----------------------------------|");
												for (Toy t : al) {
													double total = 0.0;
													total = total + (t.getQuantity() * t.getPrice());
													totalBill += total;
													System.out.println("\t\t\t\t\t | " + t.gettName() + "   "
															+ t.getColor() + "       " + t.getQuantity() + "          "
															+ total + " |");
												}
												System.out.println("\t\t\t\t\t -------------------------------------");
												System.out.println(
														"\t\t\t\t\t                  GRAND TOTAL=" + totalBill);
												System.out.println();
											} else {
												System.out.println("\n\t\t\t\tSORRY Mr. " + user.getName()
														+ ", Your cart is EMPTY. \n\t\t\t\tMight be you have not added the items to your Cart, Please Add...!\n");
											}
										}
											break;

										case 5: {
											System.out.println("\n\t\t\t\tTHANK YOU Mr. " + user.getName()
													+ " for Visiting Toy's Store Section...!");
											System.out.println("\t\t\t\tYou are Existed from Toys Stores Portal.\n");
											userChToy = false;
										}
											break;
										default: {
											System.out.println(
													"\t\t\t\tMr. " + user.getName() + ", Incorrect Input...!\n");
											break;
										}
										}
									} while (userChToy);
								}
									break;

								case 3: {
									System.out.println("\n\t\t\t\tTHANK YOU Mr. " + user.getName()
											+ ", for Visiting LOGIN Section...!");
									System.out.println(
											"\t\t\t\tMr. " + user.getName() + " Existed from LOGIN Section...!\n");
									userCh = false;
								}
									break;
								default: {
									System.out.println("Incorrect Input...!");
									break;
								}
								}
							} while (userCh);
						}
					}
						break;

					case 3: {
						System.out.println("\n\tTHANK YOU for Visiting USER Section...!");
						System.out.println("\tExisted from USER Section.\n");
						flag = false;
					}
						break;
					default: {
						System.out.println("Incorrect Input...!");
						break;
					}
					}
				} while (flag);
			}
				break;

			case 3: {
				System.out.println("\nTHANK YOU for Visiting Toy-management..!");
				System.out.println("Existed from Toy Management Portal.\n");
				key = false;
			}
				break;
			default:
				System.out.println("Incorrect Input...!");
				break;
			}
		} while (key);
	}
}
