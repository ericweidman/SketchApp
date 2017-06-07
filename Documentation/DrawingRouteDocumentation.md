path = "/save-drawing" - POST

Accepts:

JSON Object "drawing" {"title" : "sampletitle",
                       "base64result" : "samplebase64result"}

On error:

500 - "No user logged in!": Thrown if there is no current user session.

500 - "The drawing was not given a title!": Thrown if there was no title.

500 - "No drawing given!": Thrown if base64result was not present.

On success:
Returns string "Drawing saved to ..."

---

path = "/delete-drawing/{id}" - DELETE

Accepts:

Drawing id

On error:

500 - "No id given!": Thrown if id value is not given.

500 - "No user logged in!": Thrown if there is no current user session.

500 - "User cannot delete drawings of others!" : Thrown if logged in user and
                                                 drawing artist do not match.

On success:
Returns String "Drawing removed!"

---

path = "/all-user-drawings" - GET

Accepts:

No data needed.

On error:

500 - "No user logged in!"

On success:
Returns a List with every drawing by user.
