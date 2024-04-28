# **Movie-Vault (Movie Collection System)**

**Project Title:** Movie-Vault (Movie Collection System)

**Objective:** Design and develop a cutting-edge movie collection management system, harnessing the power of Azure Cloud Platform and Android technology to provide a seamless and intuitive user experience.

**Problem Statement:** Existing movie collection systems often suffer from:
* Disparate data storage and retrieval
* Clumsy user interfaces
* Limited scalability and security
* Inefficient data organization and retrieval

**Solution:** Create a comprehensive system that addresses these challenges by integrating:

Backend:
* Azure Cloud Platform for deployment
* Azure Blob storage for file storage
* Spring Boot for backend development
* Spring Security for user authentication
* Spring Data JPA for data persistence
* MySQL as the backend database

Frontend:
* Android mobile application
* Jetpack Compose for UI
* Retrofit for API integration
* Coroutine for asynchronous programming
* LiveData and MVVM for data management and architecture
* Kotlin programming language

**Goals:**
* Develop a robust and scalable movie collection system
* Ensure seamless integration and user-friendly interface
* Leverage Azure Cloud Platform and Azure Blob storage for efficient storage and retrieval
* Utilize Jetpack Compose and other frontend technologies for an intuitive and engaging user experience
* Prioritize security, scalability, and reliability in handling diverse user interactions

**Key Features:**
* User registration and login functionality
* Movie collection management and organization
* Movie trailer playback and favorites saving
* Intuitive search and filtering capabilities
* Personalized user recommendations
* Secure user data management and authentication

**Deliverables:**
* A fully functional movie collection management system
* A presentation showcasing the system's features and functionality


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


<br/>

### **Add Role**

![img.png](add_role.png)


<br/>

### **Get All Roles**

![img.png](screen/get_all_role.png)


<br/>

### **Create New User**

![create_new_user.png](screen/create_new_user.png)

<br/>

### **User Login**

![img.png](screen/user_login.png)

<br/>

### **Add All Movies (Admin)**

![img.png](screen/add_all_movies.png)

<br/>

### **Get All Movies**

![img.png](screen/get_all_movies.png)


<br/>

### **Get Profile By Id**

![img.png](screen/get_profile_by_id.png)

<br/>

### **Update Profile By Id**

![img.png](screen/update_profile.png)


<br/>

### **Add Favourite Movie**

![img.png](screen/add_favourite_movie.png)


<br/>

### **Get All Favourite Movies**

![img.png](screen/get_all_favourite_movie.png)


<br/>

### **Upload Profile Image**

It'll not work in your side, you need to set up with the Azure Account and modify the AzureStorageService.java with your connection string.

![img.png](screen/upload_profile_image.png)


<br/>

## FRONT-END (MOBILE PROJECT)

MovieVault front-end application is developed by using Native Android using Kotlin language, MVVM architecture, **Jetpack Compose** for UI, Retrofit, Coroutine, Dagger-Hilt.

Project Sample can be seen on the Github [here](https://github.com/ksmaprince/MovieValut).
