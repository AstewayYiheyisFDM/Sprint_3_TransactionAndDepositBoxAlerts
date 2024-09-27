-- Practice Data Manupulation Language and Transaction Control Language

-- 1) Run the create table statement below to create the my_employee table:

     CREATE TABLE my_employee
      (
        id 			INT 					,
        last_name   VARCHAR(15)	NOT NULL	,
        first_name  VARCHAR(15) NOT NULL	,
        userid      VARCHAR(8)	NOT NULL	,
        salary      DECIMAL(7,2) CONSTRAINT my_employee_salary_ck CHECK(salary > 0),
		CONSTRAINT my_emp_id_pk PRIMARY KEY (id)
      );  
	  
  
-- 2) Describe the structure of the my_employee table and SET AUTOCOMMIT = 0.
    desc my_employee;
    set autocommit = 0;

      
/* 3) Write an insert statement to add the FIRST ROW of data in the my_employee
      table from the following grid. Do not list the columns in the INSERT
      statement.
     ---------------------------------------------------------------------
     | ID  |  LAST_NAME    |   FIRST_NAME   |    USERID    |    SALARY   |
     ---------------------------------------------------------------------
     | 1   |  de Champlain |     Samuel     |    sdechamp  |     1608    |
     | 2   |  Douglass     |     Frederick  |    fdouglas  |     1895    |
     | 3   |  Mackenzie    |     Alexander  |    amackenz  |     793     |
     | 4   |  Madison      |     Dolly      |    dmadison  |     817     |
     | 5   |  Lincoln      |     Abraham    |    alincoln  |     1865    |
     ---------------------------------------------------------------------
 */
    insert into my_employee values(1, "de Champlain", "Samuel", "sdechamp", 1608);
	  
         
         
/* 4) Populate the my_employee table with the second row of the sample data.
        This time, list the columns explicitly in the INSERT. */    
    insert into my_employee(id, last_name, first_name, userid, salary) values(2, "Douglass", "Frederick", "fdouglas", 1895);
       
      
	  
-- 5) Confirm your additions to the table.
    select * from my_employee;
    
	
      
/* 6) With one INSERT statement, add the next two rows from the grid into the  
      my_employee table.  */   
    insert into my_employee values(3, "Mackenzie", "Alexander", "amackenz", 793), (4, "Madison", "Dolly", "dmadison", 817); 
     
	 
      
-- 7) Confirm your additions to the table.
    select * from my_employee;

      
	  

-- 8) Make the additions permanent.
    commit;
           
		   

-- 9) Change the last name of employee 3 to Owens. Change the userid accordingly.
    update my_employee
    set last_name = "Owens", userid = "aowens"
    where id = 3;


      
-- 10) Change the salary to $1000 for all employees earning less than $1000.
    update my_employee
    set salary = 1000
    where salary < 1000;
     
	 
 
-- 11) Verify your changes are correct.
    select * from my_employee;
      
	  
      
-- 12) Delete Samuel de Champlain from the my_employee table.
       delete from my_employee
       where CONCAT(first_name, " ", last_name) = "Samuel de Champlain";
      

	  
-- 13) Verify your change.
        select * from my_employee;
      
	  
      
-- 14) Make all pending changes permanent.
    commit;


     
      
/* 15) Populate my_employee with the last row of sample data.   
	   ---------------------------------------------------------        
       |  ID  | last_name    |  first_name    |    userid    |  salary  |
       ---------------------------------------------------------
	   |  5   |  Lincoln     |     Abraham    |    alincoln  |   1865   |    
	   ------------------------------------------------------------------ */
       insert into my_employee values(5, "Lincoln", "Abraham", "alincoln", 1865);
      
	  
      
-- 16) Confirm your addition to the table.
        select * from my_employee;
     
	 
        
-- 17) Create an intermediate point in your transaction.
    savepoint intermediate_point;
      
	  
           
-- 18) Delete all rows in the my_employee table.
    delete from my_employee;
        
		
                  
-- 19) Confirm the table is empty.
    select * from my_employee;
      
	  
      
/* 20) Discard the recent DELETE operation from step 18 without discarding the INSERT
		from step 15. */
    rollback to intermediate_point;
        
            
-- 21) Confirm the rows are in the table.
    select * from my_employee;

      
-- 22) Make the data changes permanent.
  commit;
         
		
/* 23) INSERT the last row. 
        ---------------------------------------------------------        
        |  ID  | last_name  |  first_name  | userid  |  salary  |
        ---------------------------------------------------------
        |  6   | Washington |  George      | gwashing|   1789   |
        ---------------------------------------------------------
*/
        insert into my_employee values(6, "Washington", "George", "gwashing", 1789);
		
      
        
-- 24) Verify the new row was added.
      select * from my_employee;
            
			
             
-- 25) Make the addition permanent.
    commit;
     
	 
           