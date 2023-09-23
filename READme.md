grit


Website Templates: 
Option 1: https://themewagon.github.io/gymlife/index.html


Problem Statement:
- Some beginners do not have access to a program and it is expensive to hire a trainer. Not everyone is able to afford a trainer per month.

Solution:
- Create a web app and an application to provide a programm for those people. This can be cheap because it is only for giving back to the community and just passive income.


Use Cases:
1. Sign-up/Login to Website:

Actors: 
- Customer (End-users/gym-goers)
- System (Fitness Web Application)

Description:
- This use case describes how an end-user can sign-up or login to the website.

Main Flow of Events:
- User clicks on the "Sign-up/Login" button.
- User is directed to the login page, he logs in if he has an account. If not, then click on Sign up at the bottom.
- After successfully logging in or signing up, direct to their personal page.


Alternative Flows:
1. If login fails, validation error messages comes up.
2. If signup page fails, validation error messages comes up.

Postconditions:
- User is validated and checked from the database if exists. (for login)
- User is added to the users table in the database after successfully signing up. (for signup)
- Redirect to personal page.

