# Database Schema

### 1. User

Represents a user in the system, typically authenticated via Kakao.

| Field           | Type          | Description                               | Constraints        |
| :-------------- | :------------ | :---------------------------------------- | :----------------- |
| `id`            | `Long`        | Primary key, auto-generated.              | `PRIMARY KEY`      |
| `kakaoId`       | `String`      | Unique ID from Kakao.                     | `NOT NULL`, `UNIQUE` |
| `nickname`      | `String`      | User's nickname.                          |                    |
| `email`         | `String`      | User's email.                             |                    |
| `profileImageUrl` | `String`      | URL to user's profile image.              |                    |
| `createdAt`     | `LocalDateTime` | Timestamp when the user was created.      |                    |
| `lastLoginAt`   | `LocalDateTime` | Timestamp of the user's last login.       |                    |

### 2. TouristSpot

Represents a tourist spot.

| Field         | Type            | Description                               | Constraints        |
| :------------ | :-------------- | :---------------------------------------- | :----------------- |
| `id`          | `Long`          | Primary key, auto-generated.              | `PRIMARY KEY`      |
| `contentId`   | `String`        | Public API content ID.                    | `NOT NULL`, `UNIQUE` |
| `title`       | `String`        | Name of the tourist spot.                 | `NOT NULL`         |
| `address`     | `String`        | Address of the tourist spot.              | `NOT NULL`         |
| `mapx`        | `Double`        | Latitude.                                 | `NOT NULL`         |
| `mapy`        | `Double`        | Longitude.                                | `NOT NULL`         |
| `info`        | `String`        | Additional information about the spot.    |                    |
| `createdAt`   | `LocalDateTime` | Timestamp when the tourist spot was created. |                    |

### 3. Course

Represents a travel course, composed of tourist spots.

| Field         | Type            | Description                               | Constraints        |
| :------------ | :-------------- | :---------------------------------------- | :----------------- |
| `id`          | `Long`          | Primary key, auto-generated.              | `PRIMARY KEY`      |
| `name`        | `String`        | Name of the course.                       |                    |
| `region`      | `String`        | Region of the course.                     |                    |
| `difficulty`  | `String`        | Difficulty level (e.g., easy, medium, hard). |                    |
| `type`        | `String`        | Type of course (e.g., nature, city).      |                    |
| `description` | `String`        | Description of the course.                |                    |
| `startSpot`   | `TouristSpot`   | Starting tourist spot of the course.      | `ManyToOne`        |
| `endSpot`     | `TouristSpot`   | Ending tourist spot of the course.        | `ManyToOne`        |

### 4. CourseSearchHistory

Records user's search history for courses.

| Field        | Type            | Description                               | Constraints        |
| :----------- | :-------------- | :---------------------------------------- | :----------------- |
| `id`         | `Long`          | Primary key, auto-generated.              | `PRIMARY KEY`      |
| `searchedAt` | `LocalDateTime` | Timestamp when the course was searched.   |                    |
| `user`       | `User`          | The user who performed the search.        | `ManyToOne`        |
| `course`     | `Course`        | The course that was searched.             | `ManyToOne`        |

### 5. Postcard

Represents a digital postcard created by a user.

| Field           | Type            | Description                               | Constraints        |
| :-------------- | :-------------- | :---------------------------------------- | :----------------- |
| `id`            | `Long`          | Primary key, auto-generated.              | `PRIMARY KEY`      |
| `imageUrl`      | `String`        | URL of the postcard image.                |                    |
| `memo`          | `String`        | User's memo on the postcard.              |                    |
| `isPublic`      | `boolean`       | Whether the postcard is public or private. |                    |
| `createdAt`     | `LocalDateTime` | Timestamp when the postcard was created.  |                    |
| `user`          | `User`          | The user who created the postcard.        | `ManyToOne`        |
| `touristSpot`   | `TouristSpot`   | The tourist spot associated with the postcard. | `ManyToOne`        |

### 6. PostcardViewHistory

Records user's viewing history for postcards.

| Field         | Type            | Description                               | Constraints        |
| :------------ | :-------------- | :---------------------------------------- | :----------------- |
| `id`          | `PostcardViewHistoryId` | Embedded ID for composite primary key.    | `EmbeddedId`       |
| `user`        | `User`          | The user who viewed the postcard.         | `ManyToOne`, `MapsId("user_id")` |
| `postcard`    | `Postcard`      | The postcard that was viewed.             | `ManyToOne`, `MapsId("postcard_id")` |
| `viewedAt`    | `LocalDateTime` | Timestamp when the postcard was viewed.   |                    |

### 7. PostcardViewHistoryId

Composite primary key for `PostcardViewHistory`.

| Field         | Type   | Description             | Constraints |
| :------------ | :----- | :---------------------- | :---------- |
| `user_id`     | `Long` | ID of the user.         |             |
| `postcard_id` | `Long` | ID of the postcard.     |             |