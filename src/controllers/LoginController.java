package controllers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.LoginModel;

public class LoginController {
	@FXML
	private Label status;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private RadioButton newCourse;

	@FXML
	private RadioButton newParticipant;

	@FXML
	private RadioButton newFaculty;

	@FXML
	private RadioButton editInfo;

	@FXML
	private RadioButton delete;

	@FXML
	private RadioButton displayInfo;

	@FXML
	private RadioButton CPName;

	@FXML
	private RadioButton CPAddress;

	@FXML
	private RadioButton CPMobNum;

	@FXML
	private RadioButton CPEmail;

	@FXML
	private RadioButton CPOrgName;

	@FXML
	private RadioButton CFName;

	@FXML
	private RadioButton CFDepartment;

	@FXML
	private RadioButton CFAddress;

	@FXML
	private RadioButton CFMobNum;

	@FXML
	private RadioButton CFEmail;

	@FXML
	private RadioButton DCourseName;

	@FXML
	private RadioButton DCourseFee;

	@FXML
	private RadioButton DStartDate;

	@FXML
	private RadioButton DDuration;

	@FXML
	private RadioButton DFaculty;

	@FXML
	private RadioButton DParticipants;

	@FXML
	private TextField courseName;

	@FXML
	private TextField courseFee;

	@FXML
	private TextField courseDuration;

	@FXML
	private TextField courseStartDate;

	@FXML
	private Label debug;

	@FXML
	private RadioButton CreateSProfile;

	@FXML
	private RadioButton RegisterSForCourse;

	@FXML
	private RadioButton CreateFaProfile;

	@FXML
	private RadioButton RegisterFForCourse;

	@FXML
	private TextField participantName;
	@FXML
	private TextField participantAddress;
	@FXML
	private TextField participantMobNum;
	@FXML
	private TextField participantEmail;

	@FXML
	private TextField participantOrgName;

	@FXML
	private ComboBox<String> ChooseStudent;

	@FXML
	private ComboBox<String> ChooseCourse;

	@FXML
	private ComboBox<String> choose;

	@FXML
	private ComboBox<String> ChooseFaculty;

	@FXML
	private ComboBox<String> ComboParticipant;

	@FXML
	private ComboBox<String> ComboFaculty;

	@FXML
	private ComboBox<String> DetailsCombo;

	@FXML
	private ComboBox<String> ChooseFCourse;

	@FXML
	private ComboBox<String> ChooseCourses;

	@FXML
	private TextField FacultyName;
	@FXML
	private TextField FacultyAddress;
	@FXML
	private TextField FacultyMobNum;
	@FXML
	private TextField FacultyEmail;

	@FXML
	private TextField Department;

	@FXML
	private RadioButton DeleteCourse;

	@FXML
	private RadioButton DeleteParticipant;

	@FXML
	private RadioButton DeleteFaculty;

	@FXML
	private TableView<Courses> table;

	@FXML
	private TableColumn<Courses, String> CourseNameid;
	@FXML
	private Button displayCourseDetails;

	private static ArrayList<Courses> courses = new ArrayList<Courses>();
	private static ArrayList<Participants> participants = new ArrayList<Participants>();
	private static ArrayList<Faculty> faculty = new ArrayList<Faculty>();

	private LoginModel model;

	public LoginController() {
		model = new LoginModel();
	}

	// Login Method to validate the credentials and allow login
	public void login() throws Exception {
		status.setText("");
		String userName = this.username.getText();
		String passWord = this.password.getText();
		if (userName == null || userName.trim().equals("")) {
			status.setText("Username cannot be empty");
			return;
		}
		if (passWord == null || passWord.trim().equals("")) {
			status.setText("Username cannot be empty");
			return;
		}
		if (userName == null || userName.trim().equals("") && (passWord == null || passWord.trim().equals(""))) {
			status.setText("Username/Password cannot be empty");
		}
		checkCredentials(userName, passWord);
	}

	public void checkCredentials(String username, String password) {
		Boolean isValid = model.getCredentials(username, password);
		if (!isValid) {
			status.setText("User does not exist");
		} else {
			status.setText("Login Successful!");
			Stage primaryStage = new Stage();
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
				Scene scene = new Scene(root, 400, 400);
				scene.getStylesheets().add(getClass().getResource("/views/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Choose an option");
				primaryStage.show();
				controller();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	// Method to reset the login screen
	public void controller() {
		username.setText("");
		password.setText("");
		status.setText("");
	}

	public void optionController() {
		Stage primaryStage = new Stage();
		Parent root = null;
		if (newCourse.isSelected()) {

			try {
				root = FXMLLoader.load(getClass().getResource("/views/NewCourse.fxml"));
				newCourse.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("New Course");

		} else if (newParticipant.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/NewParticipant.fxml"));
				newParticipant.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Choose an option");

		} else if (newFaculty.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/NewFaculty.fxml"));
				newFaculty.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Choose an option");

		} else if (editInfo.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/EditInfo.fxml"));
				editInfo.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Edit Records");

		} else if (delete.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/Delete.fxml"));
				delete.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Delete Records");
		} else if (displayInfo.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/Display.fxml"));
				displayInfo.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Display Records");
		}
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("/views/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Controller for creating a new course
	public void CreateCourseController() {

		if (Integer.parseInt(courseDuration.getText()) < 14) {
			createcourse();
			writeTofile();
			JOptionPane.showMessageDialog(null, "Course added.", "NOTE!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Duration in days should be less than two weeks.", "NOTE!",
					JOptionPane.INFORMATION_MESSAGE);
		}

		try {
			Date date = new Date();
			date = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse(courseStartDate.getText());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Date not in correct format.", "ERROR!", JOptionPane.ERROR_MESSAGE);

		}

	}

	// Getting the data entered for registering a new course and add it to the
	// courses list
	public void createcourse() {
		Courses newcourse = new Courses();
		newcourse.setcoursename(courseName.getText());
		newcourse.setcoursefee(courseFee.getText());
		newcourse.setduration(Integer.parseInt(courseDuration.getText()));
		newcourse.setstartdate(courseStartDate.getText());

		courses.add(newcourse);
	}

	// Controller for add a new participant or register a new participant to the
	// course
	@SuppressWarnings("unchecked")
	public void POptionController() {
		Stage primaryStage = new Stage();
		Parent root = null;
		if (CreateSProfile.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/ParticipantDetails.fxml"));
				CreateSProfile.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("New Participant");

		} else if (RegisterSForCourse.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/RegisterStudent.fxml"));
				RegisterSForCourse.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Register Participant");
		}
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("/views/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Method to populate the combo box with all the registered participants
	public void populateParticipants() {

		ListIterator<Participants> parti1 = participants.listIterator();
		ArrayList<String> studnames = new ArrayList<String>();
		// System.out.println(participants.get(0));
		while (parti1.hasNext()) {
			studnames.add(parti1.next().getname());
		}

		Collections.sort(studnames);
		String[] temp = new String[studnames.size()];
		temp = studnames.toArray(temp);

		ObservableList<String> studentlist = FXCollections.observableArrayList();

		for (String o : studnames) {
			studentlist.add(o);
		}
		ChooseStudent.setItems(studentlist);

	}

	public void ComboParticipant() {

		ListIterator<Participants> parti1 = participants.listIterator();
		ArrayList<String> studnames = new ArrayList<String>();
		while (parti1.hasNext()) {
			studnames.add(parti1.next().getname());
		}

		Collections.sort(studnames);
		String[] temp = new String[studnames.size()];
		temp = studnames.toArray(temp);

		ObservableList<String> studentlist = FXCollections.observableArrayList();

		for (String o : studnames) {

			studentlist.add(o);

		}

		ComboParticipant.setItems(studentlist);

	}

	public void ComboFaculty() {
		ListIterator<Faculty> fac = faculty.listIterator();
		ArrayList<String> facnames = new ArrayList<String>();

		while (fac.hasNext()) {
			facnames.add(fac.next().getname());
		}

		Collections.sort(facnames);
		String[] temp = new String[facnames.size()];
		temp = facnames.toArray(temp);

		ObservableList<String> facultyList = FXCollections.observableArrayList();

		for (String o : facnames) {

			facultyList.add(o);

		}
		ComboFaculty.setItems(facultyList);

	}

	public void DetailsCombo() {

		ListIterator<Courses> cour = courses.listIterator();
		ArrayList<String> coursenames = new ArrayList<String>();

		while (cour.hasNext()) {
			coursenames.add(cour.next().getcoursename());
		}

		Collections.sort(coursenames);
		String[] temp = new String[coursenames.size()];
		temp = coursenames.toArray(temp);

		ObservableList<String> courselist = FXCollections.observableArrayList();

		for (String o : coursenames) {

			courselist.add(o);

		}
		// System.out.println(courselist);
		DetailsCombo.setItems(courselist);

	}

	public void populateCourses() {

		ListIterator<Courses> cour = courses.listIterator();
		ArrayList<String> coursenames = new ArrayList<String>();

		while (cour.hasNext()) {
			coursenames.add(cour.next().getcoursename());
		}

		Collections.sort(coursenames);
		String[] temp = new String[coursenames.size()];
		temp = coursenames.toArray(temp);

		ObservableList<String> courselist = FXCollections.observableArrayList();

		for (String o : coursenames) {

			courselist.add(o);

		}
		// System.out.println(courselist);
		choose.setItems(courselist);

	}

	public void populateCoursess() {

		ListIterator<Courses> cour = courses.listIterator();
		ArrayList<String> coursenames = new ArrayList<String>();

		while (cour.hasNext()) {
			coursenames.add(cour.next().getcoursename());
		}

		Collections.sort(coursenames);
		String[] temp = new String[coursenames.size()];
		temp = coursenames.toArray(temp);

		ObservableList<String> courselist = FXCollections.observableArrayList();

		for (String o : coursenames) {

			courselist.add(o);

		}
		// System.out.println(courselist);
		ChooseCourses.setItems(courselist);

	}

	public void populateeCourses() {

		ListIterator<Courses> cour = courses.listIterator();
		ArrayList<String> coursenames = new ArrayList<String>();

		while (cour.hasNext()) {
			coursenames.add(cour.next().getcoursename());
		}

		Collections.sort(coursenames);
		String[] temp = new String[coursenames.size()];
		temp = coursenames.toArray(temp);

		ObservableList<String> courselist = FXCollections.observableArrayList();

		for (String o : coursenames) {

			courselist.add(o);

		}
		// System.out.println(courselist);
		ChooseCourse.setItems(courselist);

	}

	public void populateFCourses() {

		ListIterator<Courses> cour = courses.listIterator();
		ArrayList<String> coursenames = new ArrayList<String>();

		while (cour.hasNext()) {
			coursenames.add(cour.next().getcoursename());
		}

		Collections.sort(coursenames);
		String[] temp = new String[coursenames.size()];
		temp = coursenames.toArray(temp);

		ObservableList<String> courselist = FXCollections.observableArrayList();

		for (String o : coursenames) {

			courselist.add(o);

		}
		// System.out.println(courselist);
		ChooseFCourse.setItems(courselist);

	}

	public void populateFaculties() {
		ListIterator<Faculty> fac = faculty.listIterator();
		ArrayList<String> facnames = new ArrayList<String>();
		while (fac.hasNext()) {
			facnames.add(fac.next().getname());
		}
		// System.out.println(facnames);
		Collections.sort(facnames);
		String[] temp = new String[facnames.size()];
		temp = facnames.toArray(temp);

		ObservableList<String> facultyList = FXCollections.observableArrayList();

		for (String o : facnames) {

			facultyList.add(o);

		}
		ChooseFaculty.setItems(facultyList);

	}

	public void setFacultyToCourse() {
		String facname = (String) ChooseFaculty.getValue();
		String coursename = (String) ChooseFCourse.getValue();

		ListIterator<Courses> courseit = courses.listIterator();
		Courses tempcourse = new Courses();
		while (courseit.hasNext()) {
			Courses nextobj = courseit.next();
			if (nextobj.getcoursename().equals(coursename)) {
				if (nextobj.getfaclist().size() == 5) {
					JOptionPane.showMessageDialog(null, "5 faculty set already.", "REGISTRATION FAILURE",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} else {
					ListIterator<Faculty> facit = faculty.listIterator();
					while (facit.hasNext()) {
						Faculty nextfac = facit.next();// Participants nextparti = parti.next();
						if (nextfac.getname().equals(facname)) {
							nextobj.getfaclist().add(nextfac);
							writeTofile(); // Adding Faculty to Courses.
							JOptionPane.showMessageDialog(null, "Done!.", "REGISTRATION SUCCESSFUL",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				}
				break;
			}
		}

	}

	public void CreateStudentController() {
		try {

			Participants newparticipant = new Participants();
			if (participantName.getText().isEmpty() || participantAddress.getText().isEmpty()
					|| participantMobNum.getText().isEmpty() || participantOrgName.getText().isEmpty()
					|| participantEmail.getText().toString().isEmpty())
				JOptionPane.showMessageDialog(null, "Please fill all the required fields ", "SUCCESS",
						JOptionPane.INFORMATION_MESSAGE);
			else {

				if (isValid(participantEmail.getText()) && isMobNumValid(participantMobNum.getText())) {

					newparticipant.setname(participantName.getText());
					newparticipant.setaddress(participantAddress.getText());
					newparticipant.setmobnum(participantMobNum.getText());
					newparticipant.setorgname(participantOrgName.getText());
					newparticipant.setemailid(participantEmail.getText());
					participants.add(newparticipant);
					// writeTofile();
					LoginModel.writeParticipants(participants);
					JOptionPane.showMessageDialog(null, "Registered!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				}

				else {
					if (!isValid(participantEmail.getText())) {
						// System.out.println(participantEmail.getText().toString().isEmpty());
						JOptionPane.showMessageDialog(null, "Please enter valid email address", "Invalid Email",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						if (!isMobNumValid(participantMobNum.getText())) {
							JOptionPane.showMessageDialog(null, "Invalid Mobile Number!", "Invalid Mob Num",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}

		} catch (

		Exception e) {
		}

	}

	public static boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public static boolean isMobNumValid(String mobNum) {
		String regex = "[1-9]{1}[0-9]{2}[0-9]{3}[0-9]{4}";
		//String regex = "^\\(?([1-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$";
		Pattern pat = Pattern.compile(regex);

		if (mobNum == null) {
			return false;
		}
		return pat.matcher(mobNum).matches();
	}

	public void RegisterStudentController() {

		String studname1 = (String) ChooseStudent.getValue();
		String coursename1 = (String) ChooseCourses.getValue();

		ListIterator<Courses> courseit1 = courses.listIterator();
		Courses tempcourse1 = new Courses();
		while (courseit1.hasNext()) {
			Courses nextobj = courseit1.next();
			if (nextobj.getcoursename().equals(coursename1)) {
				if (nextobj.getparticipant().size() == 5) {
					JOptionPane.showMessageDialog(null, "5 participants registered already.", "REGISTRATION FAILURE",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} else {
					ListIterator<Participants> parti11 = participants.listIterator();
					while (parti11.hasNext()) {
						Participants nextparti = parti11.next();
						if (nextparti.getname().equals(studname1)) {
							nextobj.getparticipant().add(nextparti);
							writeTofile(); // Registering participant to courses
							JOptionPane.showMessageDialog(null, "Done!.", "REGISTRATION SUCCESSFUL",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				}
				break;
			}
		}

	}

	public void FPOptionController() {
		Stage primaryStage = new Stage();
		Parent root = null;
		if (CreateFaProfile.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/FacultyDetials.fxml"));
				CreateFaProfile.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("New Faculty");
		} else if (RegisterFForCourse.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("/views/RegisterFaculty.fxml"));
				RegisterFForCourse.setSelected(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			primaryStage.setTitle("Register Faculty");
		}
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("/views/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void CreateFaculty() {
		// System.out.println(FacultyMobNum.getText() + "--" +
		// participantEmail.getText());
		Faculty newfaculty = new Faculty();
		if (FacultyName.getText().isEmpty() || Department.getText().isEmpty() || FacultyAddress.getText().isEmpty()
				|| FacultyMobNum.getText().isEmpty() || FacultyEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill all the required fields ", "SUCCESS",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			// System.out.println(" I am Hre");
			if (isValid(FacultyEmail.getText()) && isMobNumValid(FacultyMobNum.getText())) {
				newfaculty.setname(FacultyName.getText());
				newfaculty.setdepartment(Department.getText());
				newfaculty.setaddress(FacultyAddress.getText());
				newfaculty.setmobnum(FacultyMobNum.getText());
				newfaculty.setemailid(FacultyEmail.getText());
				faculty.add(newfaculty);
				// writeTofile();

				LoginModel.writeFaculties(faculty);
				JOptionPane.showMessageDialog(null, "Done!", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (!isValid(FacultyEmail.getText())) {
					// System.out.println(participantEmail.getText().toString().isEmpty());
					JOptionPane.showMessageDialog(null, "Please enter valid email address", "Invalid Email",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					if (!isMobNumValid(FacultyMobNum.getText())) {
						JOptionPane.showMessageDialog(null, "Invalid Mobile Number!", "Invalid Mob Num",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}

	}

	public void DeleteController() {
		if (DeleteCourse.isSelected()) {

			String coursename = JOptionPane.showInputDialog("Enter course name:");
			ListIterator<Courses> it = courses.listIterator();

			while (it.hasNext()) {
				Courses nextcourse = it.next();
				if (nextcourse.getcoursename().equals(coursename)) {
					courses.remove(nextcourse);
					JOptionPane.showMessageDialog(null, "Deleted successfully!", "DONE",
							JOptionPane.INFORMATION_MESSAGE);
					writeTofile(); // Deleting the course
					DeleteCourse.setSelected(false);
					break;
				}
				if (!it.hasNext()) {
					JOptionPane.showMessageDialog(null, "No such course exists yet!", "FAILURE",
							JOptionPane.ERROR_MESSAGE);
					DeleteCourse.setSelected(false);

				}
			}
		} else if (DeleteParticipant.isSelected()) {

			String partiname = JOptionPane.showInputDialog("Enter participant's name:");
			ListIterator<Participants> it = participants.listIterator();

			while (it.hasNext()) {
				Participants parti = it.next();
				if (parti.getname().equals(partiname)) {
					participants.remove(parti);
					// System.out.println("Deleted successfully! ");
					ListIterator<Courses> courseit = courses.listIterator();
					LoginModel.writeParticipants(participants); // Removing a participant and writing back the
					DeleteParticipant.setSelected(false); // left ones
					while (courseit.hasNext()) {
						Courses nextcourse = courseit.next();
						ListIterator<Participants> itparti = nextcourse.getparticipant().listIterator();
						try {
							while (itparti.hasNext()) {
								Participants temp = itparti.next();
								if (temp.getname().equals(partiname)) {
									courses.get(courses.indexOf(nextcourse)).getparticipant().remove(temp);
									// nextcourse.getparticipant().remove(temp);
									break;
								}
							}
						} catch (Exception e) {
						}
					}
					JOptionPane.showMessageDialog(null, "Deleted successfully!", "DONE",
							JOptionPane.INFORMATION_MESSAGE);
					writeTofile();
					break;
				}
			}

		} else if (DeleteFaculty.isSelected()) {

			String facname = JOptionPane.showInputDialog("Enter faculty's name:");

			ListIterator<Faculty> it = faculty.listIterator();

			while (it.hasNext()) {
				Faculty nextfac = it.next();
				if (nextfac.getname().equals(facname)) {
					faculty.remove(nextfac);

					ListIterator<Courses> courseit = courses.listIterator();

					while (courseit.hasNext()) {
						Courses nextcourse = courseit.next();
						try {
							if (nextcourse.getcoordinator().getname().equals(facname)) {
								nextcourse.getcoordinator().setname(null);
								break;
							}
						} catch (Exception e) {
						}

						try {
							ListIterator<Faculty> facit = nextcourse.getfaclist().listIterator();
							while (facit.hasNext()) {
								Faculty fac = facit.next();
								if (fac.getname().equals(facname)) {
									courses.get(courses.indexOf(nextcourse)).getfaclist().remove(fac);
									// nextcourse.getfaclist().remove(fac);
									break;
								}
							}
						} catch (Exception e) {
						}
					}
					writeTofile(); // Deleting Courses
					LoginModel.writeFaculties(faculty);
					LoginModel.writeParticipants(participants);

					JOptionPane.showMessageDialog(null, "Deleted successfully!", "DONE",
							JOptionPane.INFORMATION_MESSAGE);
					DeleteFaculty.setSelected(false);
					// System.out.println("Deleted successfully! ");
					break;
				}
			}
		}
	}

	public static void writeTofile() {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			
			
			fout = new FileOutputStream("test.ser");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(courses);
			
			
			// oos.writeObject(participants);
			// oos.writeObject(faculty);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
					fout.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// written to file

	};
	
	public static void write()
	{	
		
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			File file = new File("test.ser");
			
			if(file.length()==0)
			{
				
			Courses newcourse = new Courses();
			newcourse.setcoursename("Java");
			
			Courses newcourse1 = new Courses();
			newcourse1.setcoursename("Data Analytics");
			
			Courses newcourse2 = new Courses();
			newcourse2.setcoursename("RIA");
			
			Courses newcourse3 = new Courses();
			newcourse3.setcoursename("Project Management");
			
			Courses newcourse4 = new Courses();
			newcourse4.setcoursename("Open Source Programming");
			
			Courses newcourse5 = new Courses();
			newcourse5.setcoursename("Advance Software Program");
			
			Courses newcourse6 = new Courses();
			newcourse6.setcoursename("Human/Computer Interactin");

			Courses newcourse7 = new Courses();
			newcourse7.setcoursename("Software Testing");
			
			Courses newcourse8 = new Courses();
			newcourse8.setcoursename("Business Innovation");




			courses.add(newcourse);
			courses.add(newcourse1);
			courses.add(newcourse2);
			courses.add(newcourse3);
			courses.add(newcourse4);
			courses.add(newcourse5);
			courses.add(newcourse6);
			courses.add(newcourse7);
			courses.add(newcourse8);
			}
		fout = new FileOutputStream("test.ser");
		oos = new ObjectOutputStream(fout);
		
			oos.writeObject(courses);
			
			
			
			// oos.writeObject(participants);
			// oos.writeObject(faculty);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
					fout.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// written to file
	}

	public static void readFromDatabse() {
		faculty = LoginModel.getFaculties();
		participants = LoginModel.getParticipants();
	};

	public static void readfromfile() {
		File file = new File("test.ser");
		try {
			if (!file.exists()) {// if file did not exist create new file
				file.createNewFile();
			} else {// else read from already existing file
				try {
					FileInputStream fout = new FileInputStream(file);
					ObjectInputStream oos = new ObjectInputStream(fout);
					try {
						// FileInputStream fout = new FileInputStream(file);
						// ObjectInputStream oos = new ObjectInputStream(fout);
						courses = (ArrayList<Courses>) oos.readObject();
						// participants = (ArrayList<Participants>) oos.readObject();
						// faculty = (ArrayList<Faculty>) oos.readObject();
						// System.out.println("Finished Reading! ");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "File cannot be opened", "IOException",
								JOptionPane.ERROR_MESSAGE);
						// e.printStackTrace();
					} finally {
						try {
							if (oos != null) {
								oos.close();
								fout.close();

							}
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "OutputStream cannot be closed", "IOException",
									JOptionPane.ERROR_MESSAGE);
							// e.printStackTrace();
						}
					}
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "File was not found", "FileNotFoundException",
							JOptionPane.ERROR_MESSAGE);

					// e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "InputStream error!", "IOException", JOptionPane.ERROR_MESSAGE);
					// e.printStackTrace();
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File cannot be opened", "IOException", JOptionPane.ERROR_MESSAGE);

			// e.printStackTrace();
		}

	}

	public void editParticipantDetails() {
		if (CPName.isSelected()) {
			String partiname = (String) ComboParticipant.getValue();
			ListIterator<Participants> it = participants.listIterator();
			while (it.hasNext()) {
				Participants temp = it.next();
				if (temp.getname().equals(partiname)) {
					String newname = JOptionPane.showInputDialog("Enter new name:");
					CPName.setSelected(false);
					if (newname != null) {
						temp.setname(newname);
						// writeTofile();
						LoginModel.updateParticipant(temp, "name");
						break;
					}
				}
			}

		} else if (CPMobNum.isSelected()) {
			String partiname = (String) ComboParticipant.getValue();
			ListIterator<Participants> it = participants.listIterator();
			while (it.hasNext()) {
				Participants temp = it.next();
				if (temp.getname().equals(partiname)) {
					String newnum = JOptionPane.showInputDialog("Enter new mobile number:");
					CPMobNum.setSelected(false);
					if (newnum != null && isMobNumValid(newnum)) {
						temp.setmobnum(newnum);
						// writeTofile();
						LoginModel.updateParticipant(temp, "mobNum");
						break;
					} else
						JOptionPane.showMessageDialog(null, "Invalid Mobile Number!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
				}

			}

		} else if (CPAddress.isSelected()) {
			String partiname = (String) ComboParticipant.getValue();
			ListIterator<Participants> it = participants.listIterator();
			while (it.hasNext()) {
				Participants temp = it.next();
				if (temp.getname().equals(partiname)) {
					String newaddr = JOptionPane.showInputDialog("Enter new address:");
					CPAddress.setSelected(false);
					if (newaddr != null) {
						temp.setaddress(newaddr);
						// writeTofile();
						LoginModel.updateParticipant(temp, "address");
						break;
					}
				}
			}
		} else if (CPEmail.isSelected()) {
			String partiname = (String) ComboParticipant.getValue();
			ListIterator<Participants> it = participants.listIterator();
			while (it.hasNext()) {
				Participants temp = it.next();
				if (temp.getname().equals(partiname)) {
					String newid = JOptionPane.showInputDialog("Enter new email id:");
					CPEmail.setSelected(false);
					if (newid != null) {
						temp.setemailid(newid);
						// writeTofile();
						LoginModel.updateParticipant(temp, "emailID");
						break;
					}
				}
			}
		} else if (CPOrgName.isSelected()) {
			String partiname = (String) ComboParticipant.getValue();
			ListIterator<Participants> it = participants.listIterator();
			while (it.hasNext()) {
				Participants temp = it.next();
				if (temp.getname().equals(partiname)) {
					String neworgname = JOptionPane.showInputDialog("Enter new organisation's name:");
					CPOrgName.setSelected(false);
					if (neworgname != null) {
						temp.setorgname(neworgname);
						// writeTofile();
						LoginModel.updateParticipant(temp, "orgName");
						break;
					}
				}
			}
		}

	}

	public void editFacultyDetails() {
		if (CFName.isSelected()) {
			String facname = (String) ComboFaculty.getValue();
			ListIterator<Faculty> it = faculty.listIterator();
			while (it.hasNext()) {
				Faculty temp = it.next();
				if (temp.getname().equals(facname)) {
					String newname = JOptionPane.showInputDialog("Enter new name:");
					CFName.setSelected(false);
					if (newname != null) {
						temp.setname(newname);
						// writeTofile();
						LoginModel.updateFaculty(temp, "name");
						// System.out.println(temp.getID());
						break;
					}
				}
			}
		} else if (CFAddress.isSelected()) {
			String facname = (String) ComboFaculty.getValue();
			ListIterator<Faculty> it = faculty.listIterator();
			while (it.hasNext()) {
				Faculty temp = it.next();
				if (temp.getname().equals(facname)) {
					String newaddr = JOptionPane.showInputDialog("Enter new address:");
					CFAddress.setSelected(false);
					if (newaddr != null) {
						temp.setaddress(newaddr);
						// writeTofile();
						LoginModel.updateFaculty(temp, "address");
						break;
					}
				}
			}
		} else if (CFMobNum.isSelected()) {
			String facname = (String) ComboFaculty.getValue();
			ListIterator<Faculty> it = faculty.listIterator();
			while (it.hasNext()) {
				Faculty temp = it.next();
				if (temp.getname().equals(facname)) {
					String newnum = JOptionPane.showInputDialog("Enter new mobile number:");
					CFMobNum.setSelected(false);
					if (newnum != null && isMobNumValid(newnum)) {
						temp.setmobnum(newnum);
						// writeTofile();
						LoginModel.updateFaculty(temp, "mobNum");
						break;
					} else
						JOptionPane.showMessageDialog(null, "Invalid Mobile Number!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
				}

			}

		} else if (CFEmail.isSelected()) {
			String facname = (String) ComboFaculty.getValue();
			ListIterator<Faculty> it = faculty.listIterator();
			while (it.hasNext()) {
				Faculty temp = it.next();
				if (temp.getname().equals(facname)) {
					String newid = JOptionPane.showInputDialog("Enter new email id:");
					CFEmail.setSelected(false);
					if (newid != null) {
						temp.setemailid(newid);
						// writeTofile();
						LoginModel.updateFaculty(temp, "emailID");
						break;
					}
				}
			}
		} else if (CFDepartment.isSelected()) {
			String facname = (String) ComboFaculty.getValue();
			ListIterator<Faculty> it = faculty.listIterator();
			while (it.hasNext()) {
				Faculty temp = it.next();
				if (temp.getname().equals(facname)) {
					String newdept = JOptionPane.showInputDialog("Enter new department's name:");
					CFDepartment.setSelected(false);
					if (newdept != null) {
						temp.setdepartment(newdept);
						// writeTofile();
						LoginModel.updateFaculty(temp, "department");
						break;
					}
				}
			}
		}
	}

	public void editCourseDetails() {
		if (DCourseName.isSelected()) {
			String coursename = (String) DetailsCombo.getValue();
			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				if (temp.getcoursename().equals(coursename)) {
					String newname = JOptionPane.showInputDialog("Enter new name:");
					DCourseName.setSelected(false);
					if (newname != null) {
						temp.setcoursename(newname);
						writeTofile();
						break;
					}
				}
			}
		} else if (DCourseFee.isSelected()) {
			String coursename = (String) DetailsCombo.getValue();
			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				if (temp.getcoursename().equals(coursename)) {
					String newfee = JOptionPane.showInputDialog("Enter new fee:");
					DCourseFee.setSelected(false);
					if (newfee != null) {
						temp.setcoursefee(newfee);
						writeTofile();
						break;
					}
				}
			}
		} else if (DStartDate.isSelected()) {
			String coursename = (String) DetailsCombo.getValue();
			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				if (temp.getcoursename().equals(coursename)) {
					String newdate = JOptionPane.showInputDialog("Enter new start date:");
					DStartDate.setSelected(false);
					if (newdate != null) {
						Date date = new Date();
						try {
							date = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse(newdate);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter the date in correct format(yyyy/mm/dd).",
									"ERROR!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						temp.setstartdate(newdate);
						writeTofile();
						break;
					}
				}
			}
		} else if (DDuration.isSelected()) {
			String coursename = (String) DetailsCombo.getValue();
			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				if (temp.getcoursename().equals(coursename)) {
					String newdur = JOptionPane.showInputDialog("Enter new duration:");
					DDuration.setSelected(false);
					try {
						if (Integer.parseInt(newdur) > 14) {

							JOptionPane.showMessageDialog(null, "Duration in days should be less than two weeks.",
									"NOTE!", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Enter the duration in number of days.", "ERROR!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					temp.setduration(Integer.parseInt(newdur));
					writeTofile();
					break;
				}
			}
		} else if (DFaculty.isSelected()) {
			String coursename = (String) DetailsCombo.getValue();
			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				if (temp.getcoursename().equals(coursename)) {
					String newcoordname = JOptionPane.showInputDialog("Enter new coordinator:");
					DFaculty.setSelected(false);
					ListIterator<Faculty> itnow = faculty.listIterator();

					while (itnow.hasNext()) {
						Faculty nextfac = itnow.next();
						if (nextfac.getname().equals(newcoordname)) {
							temp.setcoordinator(nextfac);////
							writeTofile();
							break;
						}
						if (!itnow.hasNext())
							JOptionPane.showMessageDialog(null, "No such faculty profile exists", "FIELD NOT EDITED",
									JOptionPane.ERROR_MESSAGE);
					}
					break;

				}
			}
		} else if (DParticipants.isSelected()) {
			String coursename = (String) DetailsCombo.getValue();
			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				if (temp.getcoursename().equals(coursename)) {
					String rmfac = JOptionPane.showInputDialog("Name of faculty to remove:");
					DParticipants.setSelected(false);
					ListIterator<Faculty> itnow = temp.getfaclist().listIterator();

					while (itnow.hasNext()) {
						Faculty nextfac = itnow.next();
						if (nextfac.getname().equals(rmfac)) {
							temp.getfaclist().remove(nextfac);
							writeTofile();
							break;
						}
						if (!itnow.hasNext()) {
							JOptionPane.showMessageDialog(null, "No such faculty profile exists", "FIELD NOT EDITED",
									JOptionPane.ERROR_MESSAGE);

						}
					}
					break;
				}
			}
		}
	}

	public void displayCourseParticipantDetails() {
		String coursename = choose.getValue();
		if (coursename != null) {
			JFrame dispcoursepartiframe = new JFrame();

			dispcoursepartiframe.setBackground(Color.GRAY);
			dispcoursepartiframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
			dispcoursepartiframe.setTitle("DISPLAYING PARTICIPANTS FOR REQUESTED COURSE");
			dispcoursepartiframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dispcoursepartiframe.setSize(new Dimension(400, 400));
			dispcoursepartiframe.setLocationRelativeTo(null);

			JPanel dispcoursepartipanel = new JPanel();
			;

			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();
				dispcoursepartipanel.setLayout(new BoxLayout(dispcoursepartipanel, BoxLayout.PAGE_AXIS));

				if (temp.getcoursename().equals(coursename)) {
					String[] columns = { "Name", "Address", "Mobile number", "Email id", "Organization's name" };
					Object[][] rows = new Object[temp.getparticipant().size()][5];
					for (Participants i : temp.getparticipant()) {
						// rows[relatives.indexOf(i)][0] = new Object();
						rows[temp.getparticipant().indexOf(i)][0] = temp.getparticipant()
								.get(temp.getparticipant().indexOf(i)).getname();
						rows[temp.getparticipant().indexOf(i)][1] = temp.getparticipant()
								.get(temp.getparticipant().indexOf(i)).getaddress();
						rows[temp.getparticipant().indexOf(i)][2] = temp.getparticipant()
								.get(temp.getparticipant().indexOf(i)).getmobnum();
						rows[temp.getparticipant().indexOf(i)][3] = temp.getparticipant()
								.get(temp.getparticipant().indexOf(i)).getemailid();
						rows[temp.getparticipant().indexOf(i)][4] = temp.getparticipant()
								.get(temp.getparticipant().indexOf(i)).getorgname();

					}

					JTable disppartidetails = new JTable(rows, columns) {
						public boolean getScrollableTracksViewportWidth() {
							return getPreferredSize().width < getParent().getWidth();
						}

						public boolean isCellEditable(int row, int col) {
							return false;
						}
					};
					disppartidetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					// disppartidetails.setAutoCreateColumnsFromModel(true);

					dispcoursepartipanel.setLayout(new BorderLayout());
					dispcoursepartipanel.add(new JScrollPane(disppartidetails), BorderLayout.CENTER);
					dispcoursepartipanel.setPreferredSize(new Dimension(350, 350));

					break;
				}
			}
			dispcoursepartiframe.add(dispcoursepartipanel);
			dispcoursepartiframe.setVisible(true);

		} else {
		}

	}

	public void displayAllParticipantDetails() {
		JFrame dispcoursepartiframe = new JFrame();

		dispcoursepartiframe.setBackground(Color.GRAY);
		dispcoursepartiframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		dispcoursepartiframe.setTitle("DISPLAYING PARTICIPANTS FOR ALL COURSEs");
		dispcoursepartiframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// add window listeners for saving data??
		// frmAddContact.setBounds(100, 100, 200, 150);//??
		dispcoursepartiframe.setSize(new Dimension(400, 400));
		dispcoursepartiframe.setLocationRelativeTo(null);

		JPanel dispcoursepartipanel = new JPanel();
		dispcoursepartipanel.setLayout(new BoxLayout(dispcoursepartipanel, BoxLayout.PAGE_AXIS));

		ListIterator<Courses> courseit = courses.listIterator();
		String[] columns = { "Name", "Address", "Mobile number", "Email id", "Organization's name",
				"Course registered for" };
		Object[][] rows = new Object[5 * courses.size()][6];

		while (courseit.hasNext()) {
			Courses temp = courseit.next();

			for (Participants i : temp.getparticipant()) {
				// rows[relatives.indexOf(i)][0] = new Object();
				rows[temp.getparticipant().indexOf(i)][0] = temp.getparticipant().get(temp.getparticipant().indexOf(i))
						.getname();
				rows[temp.getparticipant().indexOf(i)][1] = temp.getparticipant().get(temp.getparticipant().indexOf(i))
						.getaddress();
				rows[temp.getparticipant().indexOf(i)][2] = temp.getparticipant().get(temp.getparticipant().indexOf(i))
						.getmobnum();
				rows[temp.getparticipant().indexOf(i)][3] = temp.getparticipant().get(temp.getparticipant().indexOf(i))
						.getemailid();
				rows[temp.getparticipant().indexOf(i)][4] = temp.getparticipant().get(temp.getparticipant().indexOf(i))
						.getorgname();
				rows[temp.getparticipant().indexOf(i)][5] = temp.getcoursename();
			}

			// break;
		}

		JTable disppartidetails = new JTable(rows, columns) {
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		disppartidetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// disppartidetails.setAutoCreateColumnsFromModel(true);

		dispcoursepartipanel.setLayout(new BorderLayout());
		dispcoursepartipanel.add(new JScrollPane(disppartidetails), BorderLayout.CENTER);
		dispcoursepartipanel.setPreferredSize(new Dimension(350, 350));

		dispcoursepartiframe.add(dispcoursepartipanel);
		dispcoursepartiframe.setVisible(true);
	}

	public void displayAllFacultyDetails() {
		String coursename = ChooseCourse.getValue();
		if (coursename != null) {
			JFrame dispcoursefacframe = new JFrame();

			dispcoursefacframe.setBackground(Color.GRAY);
			dispcoursefacframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
			dispcoursefacframe.setTitle("DISPLAYING PARTICIPANTS FOR REQUESTED COURSE");
			dispcoursefacframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// add window listeners for saving
																					// data??
			// frmAddContact.setBounds(100, 100, 200, 150);//??
			dispcoursefacframe.setSize(new Dimension(400, 400));
			dispcoursefacframe.setLocationRelativeTo(null);

			JPanel dispcoursefacpanel = new JPanel();
			dispcoursefacpanel.setLayout(new BoxLayout(dispcoursefacpanel, BoxLayout.PAGE_AXIS));

			ListIterator<Courses> courseit = courses.listIterator();
			while (courseit.hasNext()) {
				Courses temp = courseit.next();

				if (temp.getcoursename().equals(coursename)) {

					JLabel coordlabel = new JLabel();
					coordlabel.setAlignmentX(coordlabel.CENTER_ALIGNMENT);
					if (temp.getcoordinator().getname() == null) {
						coordlabel.setText("COURSE CO-ORDINATOR NOT SET YET.");
					} else {
						coordlabel.setText("COURSE COORDINATOR:\n" + "NAME: " + temp.getcoordinator().getname()
								+ "\nDEPARTMENT: " + temp.getcoordinator().getdepartment() + "\nADDRESS: "
								+ temp.getcoordinator().getaddress() + "\nMOBILE NUMBER: "
								+ temp.getcoordinator().getmobnum() + "\nEMAIL ID: "
								+ temp.getcoordinator().getemailid() + "\n");
					}
					String[] columns = { "Name", "Department", "Address", "Mobile number", "Email id" };
					Object[][] rows = new Object[temp.getfaclist().size()][5];
					for (Faculty i : temp.getfaclist()) {
						// rows[relatives.indexOf(i)][0] = new Object();
						rows[temp.getfaclist().indexOf(i)][0] = temp.getfaclist().get(temp.getfaclist().indexOf(i))
								.getname();
						rows[temp.getfaclist().indexOf(i)][1] = temp.getfaclist().get(temp.getfaclist().indexOf(i))
								.getdepartment();
						rows[temp.getfaclist().indexOf(i)][2] = temp.getfaclist().get(temp.getfaclist().indexOf(i))
								.getaddress();
						rows[temp.getfaclist().indexOf(i)][3] = temp.getfaclist().get(temp.getfaclist().indexOf(i))
								.getmobnum();
						rows[temp.getfaclist().indexOf(i)][4] = temp.getfaclist().get(temp.getfaclist().indexOf(i))
								.getemailid();

					}

					JTable dispfacdetails = new JTable(rows, columns) {
						public boolean getScrollableTracksViewportWidth() {
							return getPreferredSize().width < getParent().getWidth();
						}

						public boolean isCellEditable(int row, int col) {
							return false;
						}
					};
					dispfacdetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					// disppartidetails.setAutoCreateColumnsFromModel(true);

					dispcoursefacpanel.setLayout(new BoxLayout(dispcoursefacpanel, BoxLayout.PAGE_AXIS));
					dispcoursefacpanel.add(Box.createRigidArea(new Dimension(0, 10)));
					dispcoursefacpanel.add(coordlabel);
					dispcoursefacpanel.add(Box.createRigidArea(new Dimension(0, 10)));
					dispcoursefacpanel.add(new JScrollPane(dispfacdetails));
					dispcoursefacpanel.add(Box.createVerticalGlue());
					dispcoursefacpanel.setPreferredSize(new Dimension(400, 400));

					break;
				}
			}
			dispcoursefacframe.add(dispcoursefacpanel);
			dispcoursefacframe.setVisible(true);
		} else {
		}
	}

	public void GeneralCourseDetails() {

		// System.out.println("hey");
		JFrame dispcoursefacframe1 = new JFrame();

		dispcoursefacframe1.setBackground(Color.GRAY);
		dispcoursefacframe1.setFont(new Font("SansSerif", Font.PLAIN, 25));
		dispcoursefacframe1.setTitle("DISPLAYING PARTICIPANTS FOR REQUESTED COURSE");
		dispcoursefacframe1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// add window listeners for saving
																				// data??
		// frmAddContact.setBounds(100, 100, 200, 150);//??
		dispcoursefacframe1.setSize(new Dimension(400, 400));
		dispcoursefacframe1.setLocationRelativeTo(null);

		JPanel dispcoursefacpanel1 = new JPanel();
		dispcoursefacpanel1.setLayout(new BoxLayout(dispcoursefacpanel1, BoxLayout.PAGE_AXIS));

		// JPanel panelDisplaycoursedetails = new JPanel();

		String[] columns = { "Name", "Coursefee", "Start date", "Duration(days)", "Course Coordinator" };
		Object[][] rows = new Object[courses.size()][5];
		for (Courses i : courses) {

			// rows[relatives.indexOf(i)][0] = new Object();
			rows[courses.indexOf(i)][0] = courses.get(courses.indexOf(i)).getcoursename();
			rows[courses.indexOf(i)][1] = courses.get(courses.indexOf(i)).getcoursefee();
			rows[courses.indexOf(i)][2] = courses.get(courses.indexOf(i)).getstartdate();
			rows[courses.indexOf(i)][3] = courses.get(courses.indexOf(i)).getduration();
			rows[courses.indexOf(i)][4] = courses.get(courses.indexOf(i)).getcoordinator().getname();

		}

		JTable dispcoursedetails = new JTable(rows, columns) {
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		dispcoursedetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dispcoursedetails.setAutoCreateColumnsFromModel(true);

		dispcoursefacpanel1.setLayout(new BorderLayout());
		dispcoursefacpanel1.add(new JScrollPane(dispcoursedetails), BorderLayout.CENTER);
		dispcoursefacpanel1.setPreferredSize(new Dimension(350, 350));

		dispcoursefacframe1.add(dispcoursefacpanel1);
		dispcoursefacframe1.setVisible(true);
	}
}
