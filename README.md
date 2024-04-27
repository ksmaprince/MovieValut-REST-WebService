# **Project Title: MovieVault: Movie Collection System**

In the realm of entertainment, the accessibility and management of movie collections have become increasingly integral to user experience. However, existing systems often lack seamless integration and intuitive interfaces, hindering users from efficiently exploring and enjoying their favorite movies. To address this challenge, the proposed project aims to develop a comprehensive movie collection system, that combine the power of Spring Boot as a backend and Android as a frontend.

### MovieVault-WebServices
The primary objective of this project is to create a robust **RESTful API web application** capable of efficiently storing, retrieving, and processing both movie and user information. Leveraging **Spring Boot** for backend development, **Spring Security** for user authentication, and **Spring Data JPA** for data persistence, the system will ensure security, scalability, and reliability in handling diverse user interactions. Furthermore, the project will implement **MySQL** as the backend database, providing a structured foundation for seamless data management. The use of **MySQL** facilitates efficient data organization and retrieval, ensuring optimal performance even with growing user databases.


# **Class Diagram**

![img.png](screen/class_diagram.png)



# **Usecase - Diagram**

![img.png](screen/use_case_diagram.png)



# **ER-Diagram**

![img.png](screen/ER-diagram.png)


## **Step to run the projects;**

1. Properly running the project requires establishing the MySql connection correctly.

2. Additionally, configure Azure Blob Storage for the file upload feature. Ensure that the AzureStorageService class in the service folder contains the accurate connection string and container.

3. Before running the project, prioritize adding the desired Roles by using "Add Role" api to your project.



### **Add Role**

![img.png](add_role.png)

### **Get All Roles**

![img.png](screen/get_all_role.png)


### **Create New User**

![create_new_user.png](screen/create_new_user.png)


### **User Login**

![img.png](screen/user_login.png)


### **Add All Movies (Admin)**

![img.png](screen/add_all_movies.png)

### **Get All Movies**

![img.png](screen/get_all_movies.png)


### **Get Profile By Id**

![img.png](screen/get_profile_by_id.png)

### **Update Profile By Id**

![img.png](screen/update_profile.png)

### **Add Favourite Movie**

![img.png](screen/add_favourite_movie.png)

### **Get All Favourite Movies**

![img.png](screen/get_all_favourite_movie.png)

### **Upload Profile Image**

It'll not work in your side, you need to set up with the Azure Account and modify the AzureStorageService.java with your connection string.

![img.png](screen/upload_profile_image.png)

