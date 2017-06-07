path = "/add-comment/{id}" - POST

Accepts:

Drawing id + JSON Object "comment" {"comment" : "samplecomment"}

On error:

500 - "No drawing id given!" Thrown if drawing id is not given.

500 - "No user logged in!" Thrown if there is no current user session.

500 - "No comment sent!" Thrown if the comment field was left blank.

On success:
Returns String "Comment saved!"

---

path = "/delete-comment/{id}" - DELETE

Accepts:

Comment id

On error:

500 - "No id given!" - Thrown if id value is not given.

On success:
Returns String "Comment deleted!"
