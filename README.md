path = /newuser - POST
Accepts:
"User" JSON Object {"username" : "sampleuser"
                    "password" : "samplepassword"}

On error:
500 - "Username not valid": Thrown if the username field is blank
                            or naming convention is invalid.

500 - "Password not valid": Thrown if the password field is blank
                            or password convention is invalid.

500 - "Username already in use": Thrown if entered username is taken.

On success:
Returns string "User created and added to database!"

---

path = /login - POST
Accepts:
"User" JSON Object {"username" : "sampleuser"
                    "password" : "samplepassword"}

On error:
500 - "Invalid username input": Thrown if the username field is blank
                                or naming convention is invalid.

500 - "Invalid password input": Thrown if password field is blank
                                or password convention is invalid.

500 - "User does not exist": Thrown if entered username is not in the DB.

500 - "Incorrect password": Thrown if entered password is incorrect.

On success:
Returns string "User authenticated!"

---

path = /logout - POST
Accepts:
No data required. - Should work on simple onClick or submit.

On error:
500 - "There is not a user currently logged in": Thrown if there is not
                                                 currently any user to
                                                 invalidate

On success:
