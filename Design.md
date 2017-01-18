## Major design of system 

+ Customer Schema: Customer {email_address, customer_first_name, customer_last_name, home_address, school_name,  customer_id, sign_up_date}
+ Document Schema: Document {customer_id,document_title, document_body, words_count, hand_in_date, mark_date, document_comment  }
+ A customer could sign up for a account, a unique customer_id will be assigned to a customer. customer_id is unique. Field of email_address must 
be unique. 
+ A customer could modify some of profile, including home_address and school_name. Rest of fields are read-only. 
+ Search: A customer could be find by searching by name, 
+ A user could create a document by copy-paste. Primary key for Document is customer_id. 

