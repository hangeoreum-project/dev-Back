# API Documentation

## Authentication API

### 1. Kakao Login
*   **Endpoint:** `/auth/kakao`
*   **Method:** `POST`
*   **Description:** Handles Kakao login and issues a JWT token upon successful authentication.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "accessToken",
          "type": "String",
          "in": "query",
          "required": true,
          "description": "The access token obtained from Kakao OAuth.",
          "example": "YOUR_KAKAO_ACCESS_TOKEN"
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "String",
      "description": "A JWT token.",
      "example": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

# Proposed API Endpoints

## 1. User Management

### Get User Profile
*   **Endpoint:** `/users/{userId}`
*   **Method:** `GET`
*   **Description:** Retrieves the profile information for a specific user.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "userId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the user."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "User profile details.",
      "example": {
        "id": 1,
        "nickname": "User1",
        "email": "user1@example.com",
        "profileImageUrl": "http://example.com/profile.jpg"
      }
    }
    ```

### Update User Profile
*   **Endpoint:** `/users/{userId}`
*   **Method:** `PUT`
*   **Description:** Updates the profile information for a specific user.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "userId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the user."
        }
      ],
      "body": {
        "type": "Object",
        "description": "User profile details to update.",
        "example": {
          "nickname": "NewNickname",
          "email": "newemail@example.com"
        }
      }
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Updated user profile details.",
      "example": {
        "id": 1,
        "nickname": "NewNickname",
        "email": "newemail@example.com",
        "profileImageUrl": "http://example.com/profile.jpg"
      }
    }
    ```

## 2. Tourist Spot Management

### Get Tourist Spot by ID
*   **Endpoint:** `/tourist-spots/{spotId}`
*   **Method:** `GET`
*   **Description:** Retrieves details of a specific tourist spot.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "spotId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the tourist spot."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Tourist spot details.",
      "example": {
        "id": 1,
        "contentId": "12345",
        "title": "Namsan Tower",
        "address": "Seoul",
        "mapx": 37.5512,
        "mapy": 126.9880,
        "info": "A landmark in Seoul."
      }
    }
    ```

### Search Tourist Spots
*   **Endpoint:** `/tourist-spots/search`
*   **Method:** `GET`
*   **Description:** Searches for tourist spots based on various criteria.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "keyword",
          "type": "String",
          "in": "query",
          "required": false,
          "description": "Keyword to search in title or info."
        },
        {
          "name": "region",
          "type": "String",
          "in": "query",
          "required": false,
          "description": "Filter by region."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Array",
      "description": "A list of tourist spot details.",
      "example": [
        {
          "id": 1,
          "contentId": "12345",
          "title": "Namsan Tower",
          "address": "Seoul",
          "mapx": 37.5512,
          "mapy": 126.9880,
          "info": "A landmark in Seoul."
        }
      ]
    }
    ```

## 3. Course Management

### Get Course by ID
*   **Endpoint:** `/courses/{courseId}`
*   **Method:** `GET`
*   **Description:** Retrieves details of a specific course.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "courseId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the course."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Course details.",
      "example": {
        "id": 1,
        "name": "Seoul City Tour",
        "region": "Seoul",
        "difficulty": "easy",
        "type": "city",
        "description": "A tour around Seoul's main attractions.",
        "startSpot": {
          "id": 1,
          "title": "Namsan Tower"
        },
        "endSpot": {
          "id": 2,
          "title": "Gyeongbokgung Palace"
        }
      }
    }
    ```

### Search Courses
*   **Endpoint:** `/courses/search`
*   **Method:** `GET`
*   **Description:** Searches for courses based on various criteria.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "keyword",
          "type": "String",
          "in": "query",
          "required": false,
          "description": "Keyword to search in course name or description."
        },
        {
          "name": "region",
          "type": "String",
          "in": "query",
          "required": false,
          "description": "Filter by region."
        },
        {
          "name": "difficulty",
          "type": "String",
          "in": "query",
          "required": false,
          "description": "Filter by difficulty."
        },
        {
          "name": "type",
          "type": "String",
          "in": "query",
          "required": false,
          "description": "Filter by type."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Array",
      "description": "A list of course details.",
      "example": [
        {
          "id": 1,
          "name": "Seoul City Tour",
          "region": "Seoul",
          "difficulty": "easy",
          "type": "city",
          "description": "A tour around Seoul's main attractions."
        }
      ]
    }
    ```

### Create Course
*   **Endpoint:** `/courses`
*   **Method:** `POST`
*   **Description:** Creates a new course.
*   **Request:**
    ```json
    {
      "body": {
        "type": "Object",
        "description": "Course details.",
        "example": {
          "name": "New Course",
          "region": "Jeju",
          "difficulty": "medium",
          "type": "nature",
          "description": "A scenic course in Jeju.",
          "startSpotId": 3,
          "endSpotId": 4
        }
      }
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Created course details.",
      "example": {
        "id": 2,
        "name": "New Course",
        "region": "Jeju",
        "difficulty": "medium",
        "type": "nature",
        "description": "A scenic course in Jeju."
      }
    }
    ```

### Update Course
*   **Endpoint:** `/courses/{courseId}`
*   **Method:** `PUT`
*   **Description:** Updates an existing course.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "courseId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the course to update."
        }
      ],
      "body": {
        "type": "Object",
        "description": "Course details to update.",
        "example": {
          "name": "Updated Course Name",
          "difficulty": "hard"
        }
      }
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Updated course details.",
      "example": {
        "id": 1,
        "name": "Updated Course Name",
        "region": "Seoul",
        "difficulty": "hard",
        "type": "city",
        "description": "A tour around Seoul's main attractions."
      }
    }
    ```

### Delete Course
*   **Endpoint:** `/courses/{courseId}`
*   **Method:** `DELETE`
*   **Description:** Deletes a course.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "courseId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the course to delete."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "String",
      "description": "Confirmation message.",
      "example": "Course deleted successfully."
    }
    ```

## 4. Postcard Management

### Get Postcard by ID
*   **Endpoint:** `/postcards/{postcardId}`
*   **Method:** `GET`
*   **Description:** Retrieves details of a specific postcard.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "postcardId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the postcard."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Postcard details.",
      "example": {
        "id": 1,
        "imageUrl": "http://example.com/postcard1.jpg",
        "memo": "Beautiful view!",
        "isPublic": true,
        "user": {
          "id": 1,
          "nickname": "User1"
        },
        "touristSpot": {
          "id": 1,
          "title": "Namsan Tower"
        }
      }
    }
    ```

### Get User's Postcards
*   **Endpoint:** `/users/{userId}/postcards`
*   **Method:** `GET`
*   **Description:** Retrieves all postcards created by a specific user.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "userId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the user."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Array",
      "description": "A list of postcard details.",
      "example": [
        {
          "id": 1,
          "imageUrl": "http://example.com/postcard1.jpg",
          "memo": "Beautiful view!",
          "isPublic": true
        }
      ]
    }
    ```

### Create Postcard
*   **Endpoint:** `/postcards`
*   **Method:** `POST`
*   **Description:** Creates a new postcard.
*   **Request:**
    ```json
    {
      "body": {
        "type": "Object",
        "description": "Postcard details.",
        "example": {
          "imageUrl": "http://example.com/new_postcard.jpg",
          "memo": "New memory!",
          "isPublic": false,
          "userId": 1,
          "touristSpotId": 5
        }
      }
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Created postcard details.",
      "example": {
        "id": 2,
        "imageUrl": "http://example.com/new_postcard.jpg",
        "memo": "New memory!",
        "isPublic": false
      }
    }
    ```

### Update Postcard
*   **Endpoint:** `/postcards/{postcardId}`
*   **Method:** `PUT`
*   **Description:** Updates an existing postcard.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "postcardId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the postcard to update."
        }
      ],
      "body": {
        "type": "Object",
        "description": "Postcard details to update.",
        "example": {
          "memo": "Updated memo!",
          "isPublic": true
        }
      }
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Updated postcard details.",
      "example": {
        "id": 1,
        "imageUrl": "http://example.com/postcard1.jpg",
        "memo": "Updated memo!",
        "isPublic": true
      }
    }
    ```

### Delete Postcard
*   **Endpoint:** `/postcards/{postcardId}`
*   **Method:** `DELETE`
*   **Description:** Deletes a postcard.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "postcardId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the postcard to delete."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "String",
      "description": "Confirmation message.",
      "example": "Postcard deleted successfully."
    }
    ```

### Update Postcard Public Status
*   **Endpoint:** `/postcards/{postcardId}/public`
*   **Method:** `PATCH`
*   **Description:** Updates the public status (isPublic) of a specific postcard.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "postcardId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the postcard to update."
        }
      ],
      "body": {
        "type": "Object",
        "description": "New public status.",
        "example": {
          "isPublic": true
        }
      }
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Object",
      "description": "Updated postcard details.",
      "example": {
        "id": 1,
        "imageUrl": "http://example.com/postcard1.jpg",
        "memo": "Beautiful view!",
        "isPublic": true
      }
    }
    ```

## 5. Course Search History Management

### Get User's Course Search History
*   **Endpoint:** `/users/{userId}/course-search-history`
*   **Method:** `GET`
*   **Description:** Retrieves the course search history for a specific user.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "userId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the user."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "Array",
      "description": "A list of course search history entries.",
      "example": [
        {
          "id": 1,
          "searchedAt": "2023-10-27T10:00:00",
          "course": {
            "id": 1,
            "name": "Seoul City Tour"
          }
        }
      ]
    }
    ```

### Delete Course Search History Entry
*   **Endpoint:** `/course-search-history/{historyId}`
*   **Method:** `DELETE`
*   **Description:** Deletes a specific course search history entry.
*   **Request:**
    ```json
    {
      "parameters": [
        {
          "name": "historyId",
          "type": "Long",
          "in": "path",
          "required": true,
          "description": "The ID of the course search history entry to delete."
        }
      ]
    }
    ```
*   **Response:**
    ```json
    {
      "type": "String",
      "description": "Confirmation message.",
      "example": "Course search history entry deleted successfully."
    }
    ```