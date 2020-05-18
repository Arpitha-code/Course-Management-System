package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controllers.Faculty;
import controllers.Participants;

public class LoginModel extends DBConnect {
public Boolean getCredentials(String username, String password) {
String query = "select * from coursemgmt_loginusers where username = ? and password = ?;";
try (PreparedStatement stmt = connection.prepareStatement(query)) {
stmt.setString(1, username);
stmt.setString(2, password);
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
return true;
}

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return false;
}

public static ArrayList<Faculty> getFaculties() {
ArrayList<Faculty> faculties = new ArrayList<Faculty>();

Statement stmt = null;
String sql = "select * from coursemgmt_faculties";
try {
stmt = connection.createStatement();
ResultSet rs = stmt.executeQuery(sql);

while (rs.next()) {
Faculty faculty = new Faculty();
faculty.setID(rs.getInt("Faculty_ID"));
faculty.setname(rs.getString("name").toString());
faculty.setmobnum(rs.getString("mobnum").toString());
faculty.setemailid(rs.getString("emailid").toString());
faculty.setdepartment(rs.getString("department").toString());
faculty.setaddress(rs.getString("address").toString());
faculties.add(faculty);
}

return faculties;
} catch (Exception e) {
e.printStackTrace();

}
return faculties;

}

public static void writeFaculties(ArrayList<Faculty> faculties) {

try {
String deleteTableData = "DELETE FROM COURSEMGMT_FACULTIES";
PreparedStatement ps1 = connection.prepareStatement(deleteTableData);
ps1.executeUpdate(deleteTableData);
String sql = "INSERT INTO COURSEMGMT_FACULTIES(NAME,DEPARTMENT,ADDRESS,MOBNUM,EMAILID) values (?,?,?,?,?)";
System.out.println("Inserting into Faculties table");
for (int i = 0; i < faculties.size(); i++) {
PreparedStatement ps = connection.prepareStatement(sql);
ps.setString(1, faculties.get(i).getname());
ps.setString(2, faculties.get(i).getdepartment());
ps.setString(3, faculties.get(i).getaddress());
ps.setString(4, faculties.get(i).getmobnum());
ps.setString(5, faculties.get(i).getemailid());
ps.executeUpdate();
}

} catch (SQLException se) {
se.printStackTrace();

}

}

public static void updateFaculty(Faculty faculty, String update) {
PreparedStatement ps;

if (update == "name") {
String sql = "UPDATE COURSEMGMT_FACULTIES SET NAME=? WHERE Faculty_ID =?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, faculty.getname());
ps.setInt(2, faculty.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "department") {
String sql = "UPDATE COURSEMGMT_FACULTIES SET DEPARTMENT=? WHERE Faculty_ID =?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, faculty.getdepartment());
ps.setInt(2, faculty.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "address") {
String sql = "UPDATE COURSEMGMT_FACULTIES SET ADDRESS=? WHERE Faculty_ID =?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, faculty.getaddress());
ps.setInt(2, faculty.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "mobNum") {
String sql = "UPDATE COURSEMGMT_FACULTIES SET MOBNUM=? WHERE Faculty_ID =?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, faculty.getmobnum());
ps.setInt(2, faculty.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "emailID") {
String sql = "UPDATE COURSEMGMT_FACULTIES SET EMAILID=? WHERE Faculty_ID =?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, faculty.getemailid());
ps.setInt(2, faculty.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}

}

public static ArrayList<Participants> getParticipants() {
ArrayList<Participants> participants = new ArrayList<Participants>();

Statement stmt = null;
String sql = "select * from coursemgmt_participants";
try {
stmt = connection.createStatement();
ResultSet rs = stmt.executeQuery(sql);

while (rs.next()) {
Participants participant = new Participants();
participant.setID(rs.getInt("Participant_ID"));
participant.setname(rs.getString("name"));
participant.setorgname(rs.getString("orgName"));
participant.setmobnum(rs.getString("mobNum"));
participant.setemailid(rs.getString("emailId"));
participant.setaddress(rs.getString("address"));
participants.add(participant);
}

return participants;
} catch (Exception e) {
e.printStackTrace();

}
return participants;

}

public static void writeParticipants(ArrayList<Participants> participants) {

try {
String deleteTableData = "DELETE FROM COURSEMGMT_PARTICIPANTS";
PreparedStatement ps1 = connection.prepareStatement(deleteTableData);
ps1.executeUpdate(deleteTableData);
String sql = "INSERT INTO COURSEMGMT_PARTICIPANTS(NAME,MOBNUM,ORGNAME,EMAILID,ADDRESS) values (?,?,?,?,?)";
System.out.println("Inserting into Participants table");
for (int i = 0; i < participants.size(); i++) {
PreparedStatement ps = connection.prepareStatement(sql);
ps.setString(1, participants.get(i).getname());
ps.setString(2, participants.get(i).getmobnum());
ps.setString(3, participants.get(i).getorgname());
ps.setString(4, participants.get(i).getemailid());
ps.setString(5, participants.get(i).getaddress());
ps.executeUpdate();
}

} catch (SQLException se) {
se.printStackTrace();

}
}

public static void updateParticipant(Participants participant, String update) {
PreparedStatement ps;
if (update == "name") {
String sql = "UPDATE COURSEMGMT_PARTICIPANTS set NAME = ? WHERE Participant_ID=?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, participant.getname());
ps.setInt(2, participant.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "mobNum") {
String sql = "UPDATE COURSEMGMT_PARTICIPANTS set MOBNUM = ? WHERE Participant_ID=?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, participant.getmobnum());
ps.setInt(2, participant.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "orgName") {
String sql = "UPDATE COURSEMGMT_PARTICIPANTS set ORGNAME = ? WHERE Participant_ID=?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, participant.getorgname());
ps.setInt(2, participant.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "emailID") {
String sql = "UPDATE COURSEMGMT_PARTICIPANTS set EMAILID = ? WHERE Participant_ID=?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, participant.getemailid());
ps.setInt(2, participant.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

} else if (update == "address") {
String sql = "UPDATE COURSEMGMT_PARTICIPANTS set ADDRESS = ? WHERE Participant_ID=?";
try {
ps = connection.prepareStatement(sql);
ps.setString(1, participant.getaddress());
ps.setInt(2, participant.getID());
ps.executeUpdate();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}
}

}
