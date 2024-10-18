package newws;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {
    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Course {
    String title;
    String description;
    List<User> enrolledUsers;

    Course(String title, String description) {
        this.title = title;
        this.description = description;
        this.enrolledUsers = new ArrayList<>();
    }

    void enroll(User user) {
        enrolledUsers.add(user);
    }

    void displayInfo(TextArea textArea) {
        textArea.append("Course Title: " + title + "\n");
        textArea.append("Description: " + description + "\n");
        textArea.append("Enrolled Users: " + enrolledUsers.size() + "\n");
        if (!enrolledUsers.isEmpty()) {
            textArea.append("User List:\n");
            for (User user : enrolledUsers) {
                textArea.append("- " + user.name + " (" + user.email + ")\n");
            }
        }
        textArea.append("\n");
    }
}

public class project extends Frame {
    private List<Course> courses;
    private TextField courseTitleField;
    private TextField courseDescriptionField;
    private TextField userNameField;
    private TextField userEmailField;
    private TextArea displayArea;

    public project() {
        courses = new ArrayList<>();

        setTitle("Online Education System");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setResizable(false);
        
        add(new Label("Course Title:"));
        courseTitleField = new TextField(20);
        add(courseTitleField);
        
        add(new Label("Course Description:"));
        courseDescriptionField = new TextField(20);
        add(courseDescriptionField);
        
        Button addCourseButton = new Button("Add Course");
        add(addCourseButton);

               add(new Label("User Name:"));
        userNameField = new TextField(20);
        add(userNameField);
        
        add(new Label("User Email:"));
        userEmailField = new TextField(20);
        add(userEmailField);
        
        Button enrollButton = new Button("Enroll User");
        add(enrollButton);

        // Display area
        displayArea = new TextArea(10, 40);
        displayArea.setEditable(false);
        add(displayArea);

        // Button actions
        addCourseButton.addActionListener(e -> addCourse());
        enrollButton.addActionListener(e -> enrollUser());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void addCourse() {
        String title = courseTitleField.getText();
        String description = courseDescriptionField.getText();
        if (!title.isEmpty() && !description.isEmpty()) {
            courses.add(new Course(title, description));
            displayArea.append("Course added: " + title + "\n");
            courseTitleField.setText("");
            courseDescriptionField.setText("");
        } else {
            displayArea.append("Please enter both title and description.\n");
        }
    }

    private void enrollUser() {
        String name = userNameField.getText();
        String email = userEmailField.getText();
        if (!name.isEmpty() && !email.isEmpty()) {
            displayArea.append("Available courses:\n");
            for (int i = 0; i < courses.size(); i++) {
                displayArea.append(i + 1 + ". " + courses.get(i).title + "\n");
            }
                      if (!courses.isEmpty()) {
                courses.get(0).enroll(new User(name, email));
                displayArea.append(name + " has enrolled in " + courses.get(0).title + "\n");
                userNameField.setText("");
                userEmailField.setText("");
            }
        } else {
            displayArea.append("Please enter both name and email.\n");
        }
    }

    public static void main(String[] args) {
        new project();
    }
}
