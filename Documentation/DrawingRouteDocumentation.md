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
