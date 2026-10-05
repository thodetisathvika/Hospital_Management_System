import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Hospital_Management_System
{
    static Scanner sc = new Scanner(System.in);

    // ---------- COMMON FILE HELPERS ----------

    static String readFile(String file)
    {
        String data = "";

        try
        {
            FileInputStream fis = new FileInputStream(file);
            int ch;

            while((ch = fis.read()) != -1)
                data += (char)ch;

            fis.close();
        }
        catch(Exception e)
        {
            return "";
        }

        return data;
    }

    static void writeFile(String file, String data) throws Exception
    {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data.getBytes());
        fos.close();
    }

    static int nextNumber(String file)
    {
        String data = readFile(file);
        int max = 0;

        if(data.trim().equals(""))
            return 1;

        String[] records = data.split("\n");

        for(String record : records)
        {
            if(record.trim().equals(""))
                continue;

            String[] p = record.trim().split("\\|");

            try
            {
                int n = Integer.parseInt(p[0]);

                if(n > max)
                    max = n;
            }
            catch(Exception e)
            {
            }
        }

        return max + 1;
    }

    static String[] findRecord(String file, String number)
    {
        String data = readFile(file);

        if(data.trim().equals(""))
            return null;

        String[] records = data.split("\n");

        for(String record : records)
        {
            if(record.trim().equals(""))
                continue;

            String[] p = record.trim().split("\\|");

            if(p.length > 0 && p[0].equals(number))
                return p;
        }

        return null;
    }

    static void pause()
    {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
    }

    static double readAmount(String message)
    {
        while(true)
        {
            try
            {
                System.out.print(message);

                double x = Double.parseDouble(sc.nextLine());

                if(x < 0)
                    throw new Exception();

                return x;
            }
            catch(Exception e)
            {
                System.out.println("Enter a valid positive amount.");
            }
        }
    }

    // ---------- 1. LOGIN ----------

    public void login()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Admin Login");
            System.out.println("2. Exit");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            if(choice == 1)
            {
                System.out.print("Enter Username: ");
                String username = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                if(username.equals("admin123") && password.equals("pass999"))
                {
                    System.out.println("Admin Login Successful!!");
                    adminMenu();
                }
                else
                {
                    System.out.println("Invalid Username/Password");
                }
            }
            else if(choice == 2)
            {
                System.out.println("Thank You For Choosing APOLLO Hospitals..");
            }
            else
            {
                System.out.println("Invalid/Wrong Choice");
            }

        }while(choice != 2);
    }

    // ---------- 2. ADMIN ----------

    public static void adminMenu()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("          ADMIN MODULE");
            System.out.println("==============================");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Staff");
            System.out.println("4. Manage Departments");
            System.out.println("5. Manage Appointments");
            System.out.println("6. Manage Rooms");
            System.out.println("7. Manage Reports");
            System.out.println("8. Manage Billing");
            System.out.println("9. Logout");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    patientModule();
                    break;

                case 2:
                    doctorModule();
                    break;

                case 3:
                    staffModule();
                    break;

                case 4:
                    departmentModule();
                    break;

                case 5:
                    appointmentModule();
                    break;

                case 6:
                    roomModule();
                    break;

                case 7:
                    reportModule();
                    break;

                case 8:
                    billingModule();
                    break;

                case 9:
                    System.out.println("Admin Logged Out Successfully!!");
                    break;

                default:
                    System.out.println("Invalid/Wrong Choice");
            }

        }while(choice != 9);
    }

    // ---------- 3. DOCTOR ----------

    public static void doctorModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("       DOCTOR MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addDoctor();
                    break;

                case 2:
                    viewDoctors();
                    break;

                case 3:
                    searchDoctor();
                    break;

                case 4:
                    updateDoctor();
                    break;

                case 5:
                    deleteDoctor();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addDoctor()
    {
        try
        {
            int no = nextNumber("doctors.txt");

            System.out.println("Doctor Number: " + no);

            System.out.print("Enter Doctor Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            String age = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Specialization: ");
            String spec = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            if(phone.length() != 10)
            {
                System.out.println("Enter 10 digit phone number.");
                return;
            }

            FileOutputStream fos =
                new FileOutputStream("doctors.txt", true);

            fos.write(
                (no + "|" + name + "|" + age + "|" +
                 gender + "|" + spec + "|" + phone + "\n").getBytes()
            );

            fos.close();

            System.out.println("Doctor added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewDoctors()
    {
        String data = readFile("doctors.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No doctor data found.");
            return;
        }

        System.out.println("\n========== DOCTORS ==========");

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] d = r.split("\\|");

            System.out.println(
                "Doctor " + d[0] +
                " | Name: " + d[1] +
                " | Age: " + d[2] +
                " | Gender: " + d[3] +
                " | Specialization: " + d[4] +
                " | Phone: " + d[5]
            );
        }
    }

    static void searchDoctor()
    {
        System.out.print("Enter Doctor Number: ");

        String no = sc.nextLine();

        String[] d = findRecord("doctors.txt", no);

        if(d == null)
        {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("\nDoctor " + d[0]);
        System.out.println("Name           : " + d[1]);
        System.out.println("Age            : " + d[2]);
        System.out.println("Gender         : " + d[3]);
        System.out.println("Specialization : " + d[4]);
        System.out.println("Phone          : " + d[5]);
    }

    static void updateDoctor()
    {
        System.out.print("Enter Doctor Number: ");

        String no = sc.nextLine();

        String data = readFile("doctors.txt");

        if(data.trim().equals(""))
        {
            System.out.println("Doctor not found.");
            return;
        }

        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] d = r.split("\\|");

            if(d[0].equals(no))
            {
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();

                System.out.print("Enter New Age: ");
                String age = sc.nextLine();

                System.out.print("Enter New Gender: ");
                String gender = sc.nextLine();

                System.out.print("Enter New Specialization: ");
                String spec = sc.nextLine();

                System.out.print("Enter New Phone: ");
                String phone = sc.nextLine();

                newData += no + "|" + name + "|" + age + "|" +
                           gender + "|" + spec + "|" + phone + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("doctors.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println(
            found ? "Doctor updated successfully."
                  : "Doctor not found."
        );
    }

    static void deleteDoctor()
    {
        System.out.print("Enter Doctor Number: ");

        String no = sc.nextLine();

        String data = readFile("doctors.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] d = r.split("\\|");

            if(d[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("doctors.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println(
            found ? "Doctor deleted successfully."
                  : "Doctor not found."
        );
    }

    // ---------- 4. STAFF ----------

    public static void staffModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("        STAFF MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Staff");
            System.out.println("2. View Staff");
            System.out.println("3. Search Staff");
            System.out.println("4. Update Staff");
            System.out.println("5. Delete Staff");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addStaff();
                    break;

                case 2:
                    viewStaff();
                    break;

                case 3:
                    searchStaff();
                    break;

                case 4:
                    updateStaff();
                    break;

                case 5:
                    deleteStaff();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addStaff()
    {
        try
        {
            int no = nextNumber("staff.txt");

            System.out.println("Staff Number: " + no);

            System.out.print("Enter Staff Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            String age = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Role: ");
            String role = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            FileOutputStream fos =
                new FileOutputStream("staff.txt", true);

            fos.write(
                (no + "|" + name + "|" + age + "|" +
                 gender + "|" + role + "|" + phone + "\n").getBytes()
            );

            fos.close();

            System.out.println("Staff added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewStaff()
    {
        String data = readFile("staff.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No staff data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] s = r.split("\\|");

            System.out.println(
                "Staff " + s[0] +
                " | " + s[1] +
                " | Age: " + s[2] +
                " | Gender: " + s[3] +
                " | Role: " + s[4] +
                " | Phone: " + s[5]
            );
        }
    }

    static void searchStaff()
    {
        System.out.print("Enter Staff Number: ");

        String no = sc.nextLine();

        String[] s = findRecord("staff.txt", no);

        if(s == null)
        {
            System.out.println("Staff not found.");
            return;
        }

        System.out.println(
            "Staff " + s[0] +
            " | Name: " + s[1] +
            " | Age: " + s[2] +
            " | Gender: " + s[3] +
            " | Role: " + s[4] +
            " | Phone: " + s[5]
        );
    }

    static void updateStaff()
    {
        System.out.print("Enter Staff Number: ");

        String no = sc.nextLine();

        String data = readFile("staff.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] s = r.split("\\|");

            if(s[0].equals(no))
            {
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();

                System.out.print("Enter New Age: ");
                String age = sc.nextLine();

                System.out.print("Enter New Gender: ");
                String gender = sc.nextLine();

                System.out.print("Enter New Role: ");
                String role = sc.nextLine();

                System.out.print("Enter New Phone: ");
                String phone = sc.nextLine();

                newData += no + "|" + name + "|" + age + "|" +
                           gender + "|" + role + "|" + phone + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("staff.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Staff updated successfully."
                  : "Staff not found."
        );
    }

    static void deleteStaff()
    {
        System.out.print("Enter Staff Number: ");

        String no = sc.nextLine();

        String data = readFile("staff.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] s = r.split("\\|");

            if(s[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("staff.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Staff deleted successfully."
                  : "Staff not found."
        );
    }

    // ---------- 5. DEPARTMENT ----------

    public static void departmentModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("     DEPARTMENT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Department");
            System.out.println("2. View Departments");
            System.out.println("3. Search Department");
            System.out.println("4. Update Department");
            System.out.println("5. Delete Department");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addDepartment();
                    break;

                case 2:
                    viewDepartments();
                    break;

                case 3:
                    searchDepartment();
                    break;

                case 4:
                    updateDepartment();
                    break;

                case 5:
                    deleteDepartment();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addDepartment()
    {
        try
        {
            int no = nextNumber("departments.txt");

            System.out.println("Department Number: " + no);

            System.out.print("Enter Department Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Head Doctor: ");
            String head = sc.nextLine();

            System.out.print("Enter Location: ");
            String loc = sc.nextLine();

            FileOutputStream fos =
                new FileOutputStream("departments.txt", true);

            fos.write(
                (no + "|" + name + "|" + head + "|" + loc + "\n").getBytes()
            );

            fos.close();

            System.out.println("Department added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewDepartments()
    {
        String data = readFile("departments.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No department data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] d = r.split("\\|");

            System.out.println(
                "Department " + d[0] +
                " | " + d[1] +
                " | Head: " + d[2] +
                " | Location: " + d[3]
            );
        }
    }

    static void searchDepartment()
    {
        System.out.print("Enter Department Number: ");

        String no = sc.nextLine();

        String[] d = findRecord("departments.txt", no);

        if(d == null)
        {
            System.out.println("Department not found.");
            return;
        }

        System.out.println(
            "Department " + d[0] +
            " | " + d[1] +
            " | Head: " + d[2] +
            " | Location: " + d[3]
        );
    }

    static void updateDepartment()
    {
        System.out.print("Enter Department Number: ");

        String no = sc.nextLine();

        String data = readFile("departments.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] d = r.split("\\|");

            if(d[0].equals(no))
            {
                System.out.print("Enter New Department Name: ");
                String name = sc.nextLine();

                System.out.print("Enter New Head Doctor: ");
                String head = sc.nextLine();

                System.out.print("Enter New Location: ");
                String loc = sc.nextLine();

                newData += no + "|" + name + "|" + head + "|" +
                           loc + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("departments.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Department updated successfully."
                  : "Department not found."
        );
    }

    static void deleteDepartment()
    {
        System.out.print("Enter Department Number: ");

        String no = sc.nextLine();

        String data = readFile("departments.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] d = r.split("\\|");

            if(d[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("departments.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Department deleted successfully."
                  : "Department not found."
        );
    }
 // ---------- 6. APPOINTMENT ----------

    public static void appointmentModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("      APPOINTMENT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Search Appointment");
            System.out.println("4. Update Appointment");
            System.out.println("5. Delete Appointment");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addAppointment();
                    break;

                case 2:
                    viewAppointments();
                    break;

                case 3:
                    searchAppointment();
                    break;

                case 4:
                    updateAppointment();
                    break;

                case 5:
                    deleteAppointment();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addAppointment()
    {
        try
        {
            int no = nextNumber("appointments.txt");

            System.out.println("Appointment Number: " + no);

            System.out.print("Enter Patient Number: ");
            String pno = sc.nextLine();

            String[] p = findRecord("patients.txt", pno);

            if(p == null)
            {
                System.out.println("Patient not found.");
                return;
            }

            System.out.print("Enter Doctor Number: ");
            String dno = sc.nextLine();

            String[] d = findRecord("doctors.txt", dno);

            if(d == null)
            {
                System.out.println("Doctor not found.");
                return;
            }

            System.out.print("Enter Appointment Date: ");
            String date = sc.nextLine();

            System.out.print("Enter Appointment Time: ");
            String time = sc.nextLine();

            FileOutputStream fos =
                new FileOutputStream("appointments.txt", true);

            fos.write(
                (no + "|" + pno + "|" + p[1] + "|" +
                 dno + "|" + d[1] + "|" + date + "|" +
                 time + "\n").getBytes()
            );

            fos.close();

            System.out.println("Appointment added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewAppointments()
    {
        String data = readFile("appointments.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No appointment data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] a = r.split("\\|");

            System.out.println(
                "Appointment " + a[0] +
                " | Patient: " + a[2] +
                " | Doctor: " + a[4] +
                " | Date: " + a[5] +
                " | Time: " + a[6]
            );
        }
    }

    static void searchAppointment()
    {
        System.out.print("Enter Appointment Number: ");

        String no = sc.nextLine();

        String[] a = findRecord("appointments.txt", no);

        if(a == null)
        {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("Appointment " + a[0]);
        System.out.println("Patient Name : " + a[2]);
        System.out.println("Doctor Name  : " + a[4]);
        System.out.println("Date         : " + a[5]);
        System.out.println("Time         : " + a[6]);
    }

    static void updateAppointment()
    {
        System.out.print("Enter Appointment Number: ");

        String no = sc.nextLine();

        String data = readFile("appointments.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] a = r.split("\\|");

            if(a[0].equals(no))
            {
                System.out.print("Enter New Patient Number: ");
                String pno = sc.nextLine();

                String[] p = findRecord("patients.txt", pno);

                if(p == null)
                {
                    System.out.println("Patient not found.");
                    return;
                }

                System.out.print("Enter New Doctor Number: ");
                String dno = sc.nextLine();

                String[] d = findRecord("doctors.txt", dno);

                if(d == null)
                {
                    System.out.println("Doctor not found.");
                    return;
                }

                System.out.print("Enter New Date: ");
                String date = sc.nextLine();

                System.out.print("Enter New Time: ");
                String time = sc.nextLine();

                newData += no + "|" + pno + "|" + p[1] +
                           "|" + dno + "|" + d[1] +
                           "|" + date + "|" + time + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("appointments.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Appointment updated successfully."
                  : "Appointment not found."
        );
    }

    static void deleteAppointment()
    {
        System.out.print("Enter Appointment Number: ");

        String no = sc.nextLine();

        String data = readFile("appointments.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] a = r.split("\\|");

            if(a[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("appointments.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Appointment deleted successfully."
                  : "Appointment not found."
        );
    }
 // ---------- 7. ROOM ----------

    public static void roomModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("         ROOM MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Search Room");
            System.out.println("4. Update Room");
            System.out.println("5. Delete Room");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addRoom();
                    break;

                case 2:
                    viewRooms();
                    break;

                case 3:
                    searchRoom();
                    break;

                case 4:
                    updateRoom();
                    break;

                case 5:
                    deleteRoom();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addRoom()
    {
        try
        {
            int no = nextNumber("rooms.txt");

            System.out.println("Room Number: " + no);

            System.out.print("Enter Room Type: ");
            String type = sc.nextLine();

            double charge = readAmount("Enter Room Charge: ");

            FileOutputStream fos =
                new FileOutputStream("rooms.txt", true);

            fos.write(
                (no + "|" + type + "|" + charge + "\n").getBytes()
            );

            fos.close();

            System.out.println("Room added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewRooms()
    {
        String data = readFile("rooms.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No room data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] x = r.split("\\|");

            System.out.println(
                "Room " + x[0] +
                " | Type: " + x[1] +
                " | Charge: " + x[2]
            );
        }
    }

    static void searchRoom()
    {
        System.out.print("Enter Room Number: ");

        String no = sc.nextLine();

        String[] x = findRecord("rooms.txt", no);

        if(x == null)
        {
            System.out.println("Room not found.");
            return;
        }

        System.out.println(
            "Room " + x[0] +
            " | Type: " + x[1] +
            " | Charge: " + x[2]
        );
    }

    static void updateRoom()
    {
        System.out.print("Enter Room Number: ");

        String no = sc.nextLine();

        String data = readFile("rooms.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] x = r.split("\\|");

            if(x[0].equals(no))
            {
                System.out.print("Enter New Room Type: ");
                String type = sc.nextLine();

                double charge =
                    readAmount("Enter New Room Charge: ");

                newData += no + "|" + type + "|" +
                           charge + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("rooms.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Room updated successfully."
                  : "Room not found."
        );
    }

    static void deleteRoom()
    {
        System.out.print("Enter Room Number: ");

        String no = sc.nextLine();

        String data = readFile("rooms.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] x = r.split("\\|");

            if(x[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("rooms.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Room deleted successfully."
                  : "Room not found."
        );
    }
 // ---------- 8. PATIENT ----------

    public static void patientModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("       PATIENT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addPatient();
                    break;

                case 2:
                    viewPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addPatient()
    {
        try
        {
            int no = nextNumber("patients.txt");

            System.out.println("Patient Number: " + no);

            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            String age = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Disease: ");
            String disease = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            if(phone.length() != 10)
            {
                System.out.println(
                    "Enter 10 digit mobile number."
                );
                return;
            }

            FileOutputStream fos =
                new FileOutputStream("patients.txt", true);

            fos.write(
                (no + "|" + name + "|" + age + "|" +
                 gender + "|" + disease + "|" +
                 phone + "\n").getBytes()
            );

            fos.close();

            System.out.println("Patient added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewPatients()
    {
        String data = readFile("patients.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No patient data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] p = r.split("\\|");

            System.out.println(
                "Patient " + p[0] +
                " | Name: " + p[1] +
                " | Age: " + p[2] +
                " | Gender: " + p[3] +
                " | Disease: " + p[4] +
                " | Phone: " + p[5]
            );
        }
    }

    static void searchPatient()
    {
        System.out.print("Enter Patient Number: ");

        String no = sc.nextLine();

        String[] p = findRecord("patients.txt", no);

        if(p == null)
        {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Patient " + p[0]);
        System.out.println("Name    : " + p[1]);
        System.out.println("Age     : " + p[2]);
        System.out.println("Gender  : " + p[3]);
        System.out.println("Disease : " + p[4]);
        System.out.println("Phone   : " + p[5]);
    }

    static void updatePatient()
    {
        System.out.print("Enter Patient Number: ");

        String no = sc.nextLine();

        String data = readFile("patients.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] p = r.split("\\|");

            if(p[0].equals(no))
            {
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();

                System.out.print("Enter New Age: ");
                String age = sc.nextLine();

                System.out.print("Enter New Gender: ");
                String gender = sc.nextLine();

                System.out.print("Enter New Disease: ");
                String disease = sc.nextLine();

                System.out.print("Enter New Phone: ");
                String phone = sc.nextLine();

                newData += no + "|" + name + "|" + age +
                           "|" + gender + "|" + disease +
                           "|" + phone + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("patients.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Patient updated successfully."
                  : "Patient not found."
        );
    }

    static void deletePatient()
    {
        System.out.print("Enter Patient Number: ");

        String no = sc.nextLine();

        String data = readFile("patients.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] p = r.split("\\|");

            if(p[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("patients.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Patient deleted successfully."
                  : "Patient not found."
        );
    }
 // ---------- 9. REPORT ----------

    public static void reportModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("         REPORT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Report");
            System.out.println("2. View Reports");
            System.out.println("3. Search Report");
            System.out.println("4. Update Report");
            System.out.println("5. Delete Report");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addReport();
                    break;

                case 2:
                    viewReports();
                    break;

                case 3:
                    searchReport();
                    break;

                case 4:
                    updateReport();
                    break;

                case 5:
                    deleteReport();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addReport()
    {
        try
        {
            int no = nextNumber("reports.txt");

            System.out.println("Report Number: " + no);

            System.out.print("Enter Patient Number: ");
            String pno = sc.nextLine();

            String[] p = findRecord("patients.txt", pno);

            if(p == null)
            {
                System.out.println("Patient not found.");
                return;
            }

            System.out.print("Enter Doctor Number: ");
            String dno = sc.nextLine();

            String[] d = findRecord("doctors.txt", dno);

            if(d == null)
            {
                System.out.println("Doctor not found.");
                return;
            }

            System.out.print("Enter Report Type: ");
            String type = sc.nextLine();

            FileOutputStream fos =
                new FileOutputStream("reports.txt", true);

            fos.write(
                (no + "|" + pno + "|" + p[1] +
                 "|" + dno + "|" + d[1] +
                 "|" + type + "\n").getBytes()
            );

            fos.close();

            System.out.println("Report added successfully.");
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewReports()
    {
        String data = readFile("reports.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No report data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] x = r.split("\\|");

            System.out.println(
                "Report " + x[0] +
                " | Patient: " + x[2] +
                " | Doctor: " + x[4] +
                " | Type: " + x[5]
            );
        }
    }

    static void searchReport()
    {
        System.out.print("Enter Report Number: ");

        String no = sc.nextLine();

        String[] x = findRecord("reports.txt", no);

        if(x == null)
        {
            System.out.println("Report not found.");
            return;
        }

        System.out.println(
            "Report " + x[0] +
            " | Patient: " + x[2] +
            " | Doctor: " + x[4] +
            " | Type: " + x[5]
        );
    }

    static void updateReport()
    {
        System.out.print("Enter Report Number: ");

        String no = sc.nextLine();

        String data = readFile("reports.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] x = r.split("\\|");

            if(x[0].equals(no))
            {
                System.out.print("Enter New Patient Number: ");
                String pno = sc.nextLine();

                String[] p = findRecord("patients.txt", pno);

                if(p == null)
                {
                    System.out.println("Patient not found.");
                    return;
                }

                System.out.print("Enter New Doctor Number: ");
                String dno = sc.nextLine();

                String[] d = findRecord("doctors.txt", dno);

                if(d == null)
                {
                    System.out.println("Doctor not found.");
                    return;
                }

                System.out.print("Enter New Report Type: ");
                String type = sc.nextLine();

                newData += no + "|" + pno + "|" + p[1] +
                           "|" + dno + "|" + d[1] +
                           "|" + type + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("reports.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Report updated successfully."
                  : "Report not found."
        );
    }

    static void deleteReport()
    {
        System.out.print("Enter Report Number: ");

        String no = sc.nextLine();

        String data = readFile("reports.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] x = r.split("\\|");

            if(x[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("reports.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(
            found ? "Report deleted successfully."
                  : "Report not found."
        );
    }
 // ---------- 10. BILLING ----------

    public static void billingModule()
    {
        int choice;

        do
        {
            System.out.println("\n==============================");
            System.out.println("         BILLING MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Generate Bill");
            System.out.println("2. View Bills");
            System.out.println("3. Search Bill");
            System.out.println("4. Update Bill");
            System.out.println("5. Delete Bill");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch(choice)
            {
                case 1:
                    addBill();
                    break;

                case 2:
                    viewBills();
                    break;

                case 3:
                    searchBill();
                    break;

                case 4:
                    updateBill();
                    break;

                case 5:
                    deleteBill();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice != 6);
    }

    static void addBill()
    {
        try
        {
            int no = nextNumber("billing.txt");

            System.out.println("Bill Number: " + no);

            System.out.print("Enter Patient Number: ");
            String pno = sc.nextLine();

            String[] p = findRecord("patients.txt", pno);

            if(p == null)
            {
                System.out.println("Patient not found.");
                return;
            }

            System.out.print("Enter Doctor Number: ");
            String dno = sc.nextLine();

            String[] d = findRecord("doctors.txt", dno);

            if(d == null)
            {
                System.out.println("Doctor not found.");
                return;
            }

            System.out.print("Enter Room Number: ");
            String rno = sc.nextLine();

            String[] room = findRecord("rooms.txt", rno);

            if(room == null)
            {
                System.out.println("Room not found.");
                return;
            }

            System.out.print("Enter Appointment Number: ");
            String ano = sc.nextLine();

            String[] a = findRecord("appointments.txt", ano);

            if(a == null)
            {
                System.out.println("Appointment not found.");
                return;
            }

            double consultation =
                readAmount("Enter Consultation Charge: ");

            double roomCharge =
                readAmount("Enter Room Charge: ");

            double medicine =
                readAmount("Enter Medicine Charge: ");

            double lab =
                readAmount("Enter Lab/Test Charge: ");

            double nursing =
                readAmount("Enter Nursing Charge: ");

            double discharge =
                readAmount("Enter Discharge Charge: ");

            double other =
                readAmount("Enter Other Charges: ");

            double total =
                consultation + roomCharge + medicine +
                lab + nursing + discharge + other;

            double paid =
                readAmount("Enter Amount Paid: ");

            double balance = total - paid;

            FileOutputStream fos =
                new FileOutputStream("billing.txt", true);

            String record =
                no + "|" +
                p[1] + "|" +
                p[4] + "|" +
                d[1] + "|" +
                room[1] + "|" +
                a[5] + "|" +
                a[6] + "|" +
                consultation + "|" +
                roomCharge + "|" +
                medicine + "|" +
                lab + "|" +
                nursing + "|" +
                discharge + "|" +
                other + "|" +
                total + "|" +
                paid + "|" +
                balance + "\n";

            fos.write(record.getBytes());
            fos.close();

            printBill(
                String.valueOf(no),
                p[1],
                p[4],
                d[1],
                a[5],
                a[6],
                consultation,
                roomCharge,
                medicine,
                lab,
                nursing,
                discharge,
                other,
                total,
                paid,
                balance
            );
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void printBill(
        String no,
        String patient,
        String disease,
        String doctor,
        String date,
        String time,
        double consultation,
        double room,
        double medicine,
        double lab,
        double nursing,
        double discharge,
        double other,
        double total,
        double paid,
        double balance)
    {
        System.out.println("\n============================================");
        System.out.println("              APOLLO HOSPITALS");
        System.out.println("                 BILL RECEIPT");
        System.out.println("============================================");

        System.out.println("Bill Number       : " + no);
        System.out.println("Patient Name      : " + patient);
        System.out.println("Disease           : " + disease);
        System.out.println("Doctor Name       : " + doctor);
        System.out.println("Appointment Date  : " + date);
        System.out.println("Appointment Time  : " + time);

        System.out.println("--------------------------------------------");
        System.out.println("                BILL DETAILS");
        System.out.println("--------------------------------------------");

        System.out.printf(
            "Consultation Charge       : %.2f%n",
            consultation
        );

        System.out.printf(
            "Room Charge               : %.2f%n",
            room
        );

        System.out.printf(
            "Medicine Charge           : %.2f%n",
            medicine
        );

        System.out.printf(
            "Lab/Test Charge           : %.2f%n",
            lab
        );

        System.out.printf(
            "Nursing Charge            : %.2f%n",
            nursing
        );

        System.out.printf(
            "Discharge Charge          : %.2f%n",
            discharge
        );

        System.out.printf(
            "Other Charges             : %.2f%n",
            other
        );

        System.out.println("--------------------------------------------");

        System.out.printf(
            "TOTAL AMOUNT              : %.2f%n",
            total
        );

        System.out.printf(
            "AMOUNT PAID               : %.2f%n",
            paid
        );

        if(balance > 0)
        {
            System.out.printf(
                "BALANCE AMOUNT            : %.2f%n",
                balance
            );
        }
        else if(balance == 0)
        {
            System.out.println(
                "BALANCE AMOUNT            : 0.00\n" +
                "Payment Status            : PAID"
            );
        }
        else
        {
            System.out.printf(
                "EXCESS AMOUNT             : %.2f%n",
                Math.abs(balance)
            );
        }

        System.out.println("--------------------------------------------");
        System.out.println("                 THANK YOU!");
        System.out.println("============================================");
    }

    static void viewBills()
    {
        String data = readFile("billing.txt");

        if(data.trim().equals(""))
        {
            System.out.println("No billing data found.");
            return;
        }

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] b = r.split("\\|");

            System.out.println("\nBill " + b[0]);
            System.out.println("Patient: " + b[1]);
            System.out.println("Disease: " + b[2]);
            System.out.println("Doctor : " + b[3]);
            System.out.println("Date   : " + b[5]);
            System.out.println("Time   : " + b[6]);

            System.out.printf(
                "Total  : %.2f%n",
                Double.parseDouble(b[14])
            );

            System.out.printf(
                "Paid   : %.2f%n",
                Double.parseDouble(b[15])
            );

            System.out.printf(
                "Balance: %.2f%n",
                Double.parseDouble(b[16])
            );
        }
    }

    static void searchBill()
    {
        System.out.print("Enter Bill Number: ");

        String no = sc.nextLine();

        String[] b = findRecord("billing.txt", no);

        if(b == null)
        {
            System.out.println("Bill not found.");
            return;
        }

        printBill(
            b[0],
            b[1],
            b[2],
            b[3],
            b[5],
            b[6],
            Double.parseDouble(b[7]),
            Double.parseDouble(b[8]),
            Double.parseDouble(b[9]),
            Double.parseDouble(b[10]),
            Double.parseDouble(b[11]),
            Double.parseDouble(b[12]),
            Double.parseDouble(b[13]),
            Double.parseDouble(b[14]),
            Double.parseDouble(b[15]),
            Double.parseDouble(b[16])
        );
    }

    static void updateBill()
    {
        System.out.print("Enter Bill Number: ");

        String no = sc.nextLine();

        String data = readFile("billing.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] b = r.split("\\|");

            if(b[0].equals(no))
            {
                System.out.print("Enter New Patient Number: ");
                String pno = sc.nextLine();

                String[] p = findRecord("patients.txt", pno);

                if(p == null)
                {
                    System.out.println("Patient not found.");
                    return;
                }

                System.out.print("Enter New Doctor Number: ");
                String dno = sc.nextLine();

                String[] d = findRecord("doctors.txt", dno);

                if(d == null)
                {
                    System.out.println("Doctor not found.");
                    return;
                }
                System.out.print("Enter New Room Number: ");
                String rno = sc.nextLine();

                String[] room =
                    findRecord("rooms.txt", rno);

                if(room == null)
                {
                    System.out.println("Room not found.");
                    return;
                }

                System.out.print("Enter New Appointment Number: ");
                String ano = sc.nextLine();

                String[] a =
                    findRecord("appointments.txt", ano);

                if(a == null)
                {
                    System.out.println("Appointment not found.");
                    return;
                }

                double consultation =
                    readAmount("Enter New Consultation Charge: ");

                double roomCharge =
                    readAmount("Enter New Room Charge: ");

                double medicine =
                    readAmount("Enter New Medicine Charge: ");

                double lab =
                    readAmount("Enter New Lab/Test Charge: ");

                double nursing =
                    readAmount("Enter New Nursing Charge: ");

                double discharge =
                    readAmount("Enter New Discharge Charge: ");

                double other =
                    readAmount("Enter New Other Charges: ");

                double total =
                    consultation + roomCharge + medicine +
                    lab + nursing + discharge + other;

                double paid =
                    readAmount("Enter New Amount Paid: ");

                double balance = total - paid;

                newData +=
                    no + "|" +
                    p[1] + "|" +
                    p[4] + "|" +
                    d[1] + "|" +
                    room[1] + "|" +
                    a[5] + "|" +
                    a[6] + "|" +
                    consultation + "|" +
                    roomCharge + "|" +
                    medicine + "|" +
                    lab + "|" +
                    nursing + "|" +
                    discharge + "|" +
                    other + "|" +
                    total + "|" +
                    paid + "|" +
                    balance + "\n";

                found = true;
            }
            else
            {
                newData += r + "\n";
            }
        }

        try
        {
            writeFile("billing.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println(
            found ? "Bill updated successfully."
                  : "Bill not found."
        );
    }

    static void deleteBill()
    {
        System.out.print("Enter Bill Number: ");

        String no = sc.nextLine();

        String data = readFile("billing.txt");
        String newData = "";
        boolean found = false;

        for(String r : data.split("\n"))
        {
            if(r.trim().equals(""))
                continue;

            String[] b = r.split("\\|");

            if(b[0].equals(no))
                found = true;
            else
                newData += r + "\n";
        }

        try
        {
            writeFile("billing.txt", newData);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println(
            found ? "Bill deleted successfully."
                  : "Bill not found."
        );
    }

    // ---------- MAIN METHOD ----------

    public static void main(String[] args)
    {
        Hospital_Management_System h =
            new Hospital_Management_System();

        h.login();
    }
}