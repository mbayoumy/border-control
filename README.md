## Use case:
 #### Input: 
    1- Image of the scanned passport
    2- Image of the scanned picture of the person
 
#### Logic
    1- Get passport info from the image
    2- If its a UK or EU passport check its validity in our DB otherwise fail
    3- Check its not expired
    4- Check the picture matches the one in the passport
    5- Record the entry in an audit event 


