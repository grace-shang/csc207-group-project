# week3-project
CSC207 Week 3 Project

**Description of the Problem Domain: **

The problem domain that we have chosen for this project is the Time Management System/ Scheduling domain. We would like to develop a highly functional and easily accessible all-in-one personal helper that betters an individual's life by creating a manageable workflow with their schedules. Some potential functionalities we are considering include planning tasks and tracking their progress, setting reminders, creating/editing schedules, and marking important dates. The problem domain expands towards accessability for all individuals, creating an application that everyone can enjoy.


**Description of the Application:**

We want to create a personal planner that is linked with studies and geared towards the University of Toronto's timetable. It will allow people to input their school schedules, list their tasks and which courses they belong to, note things they want as reminders, plan their future courses, and other things on along that line. It allows students to keep all their schooling information on one software and ensure they are completing everything they need to.


**API's We Can Use: **

1. Todoist API enables us to add, delete, write, and read items on the Todo list. We can try to connect this to our software in order to optimize the personal helper asepct of our application and add it to the timetable if possible. This API would also allow us to keep track of the users on our software and help us manage all the users' actions, such as adding their accounts, deleting the user profile from the application, updating their student information (graduating year, program, etc.), and other similar use cases. 

https://developer.todoist.com/guides/#developing-with-todoist


2. Google Calendar API could be something we use to create the calendar and handle reminders along with it. It reads and updates calendars, and we can try to connect it to our application so our users can view and create new events to input into their calendar. 

https://developers.google.com/calendar?ref=apilist.fun


**Screenshot Using A Tool to Try the API:**
<img width="1430" alt="Screenshot 2023-09-29 at 4 13 04 PM" src="https://github.com/grace-shang/week3-project/assets/128920671/c1d5b70b-2add-49a0-a5c5-ee5a10e8923c">

This image includes the Todoist API in Postman after all the functions were uploaded following their procedures. With this image, on the left it showcases all the functions/helpers of the Todoist API that will help us create our program. It has a helper that will help us manage the user profiles with the following functions: creates users, login users, logs them out of the software, updates a user profile, uploads an image, gets a user image, deletes the image and deletes the user. It also will also implement our application's "To Do" feature as the API has the functionionality to add a task, get all the tasks, get task by ID, get task by completion, get task by pagination, update task by ID, and delete task by ID. On the top right side of the screenshot, we have an example of what a user could be represented by, including the information needed to register a user. The bottom right side shows the code of the API.
