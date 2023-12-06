# Group Project
CSC207 Group Project

**Description of the Problem Domain:**

The problem domain that we have chosen for this project is the Scheduling domain. We would like to develop a highly functional and easily accessible all-in-one personal helper that betters an individual's life by creating a list with all the tasks the have to manage on one page. Some potential functionalities we are considering include listing tasks and completing them when they are done with their assignment. The problem domain expands towards accessability for all individuals, creating an application that everyone can enjoy and use that will help them manage their time and what they have to do. 


**Description of the Application:**

An app that lists all the tasks someone has to complete that lays it all on the table for them. With our app people will be able to input their tasks into the program which will then store it and have it ready for them to view any time they need it. The app with keep all their tasks in memory and any time they open the app it will have the tasks they already inputted into it in the order they inputted it in. The tasks will also have a complete button so that users can hit complete on all the tasks they have already done and have the gratification of hitting that check box and completing their task. 

**API's We Can Use:**

1. Todoist API enables us to add, delete, write, and read items on the Todo list. We can try to connect this to our software in order to optimize the personal helper asepct of our application and add it to the timetable if possible. This API would also allow us to keep track of the users on our software and help us manage all the users' actions, such as adding their accounts, deleting the user profile from the application, updating their student information (graduating year, program, etc.), and other similar use cases. 

https://developer.todoist.com/guides/#developing-with-todoist


2. Google Calendar API could be something we use to create the calendar and handle reminders along with it. It reads and updates calendars, and we can try to connect it to our application so our users can view and create new events to input into their calendar. 

https://developers.google.com/calendar?ref=apilist.fun


**Screenshot Using A Tool to Try the API:**
<img width="1430" alt="Screenshot 2023-09-29 at 4 13 04 PM" src="https://github.com/grace-shang/week3-project/assets/128920671/c1d5b70b-2add-49a0-a5c5-ee5a10e8923c">

This image includes the Todoist API in Postman after all the functions were uploaded following their procedures. With this image, on the left it showcases all the functions/helpers of the Todoist API that will help us create our program. It has a helper that will help us manage the user profiles with the following functions: creates users, login users, logs them out of the software, updates a user profile, uploads an image, gets a user image, deletes the image and deletes the user. It also will also implement our application's "To Do" feature as the API has the functionionality to add a task, get all the tasks, get task by ID, get task by completion, get task by pagination, update task by ID, and delete task by ID. On the top right side of the screenshot, we have an example of what a user could be represented by, including the information needed to register a user. The bottom right side shows the code of the API.

**API's We Did Use:**
We used the Todoist API to create our Task Tracker. It aided with the creation of tasks as every task made runs through the API and creates the task which then gets sent back to the app. The app then displays the task that was just added by the user. The API helps create the task and we manage it! 


**Software Specification**
This app is exactly its name: Task Tracker. Our app allows users to input their tasks into it. We then list their tasks in the order they gave to us and keep it ready for their use whenever they want to use it. The app also allows the user to check the box of the task they completed so they know what has been done and what has not. This app allows users to see everything they still have to do and to make sure they plan accordingly. 
