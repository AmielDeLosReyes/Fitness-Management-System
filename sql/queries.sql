select * from workouts;
select * from user;
select * from userPrograms;
select * from program_workouts;
select * from user_workouts;
select * from programs;

SELECT p.program_id, w.workout_name, w.workout_location, uw.program_id, w.workout_id, p.program_name
FROM programs p
INNER JOIN user_workouts uw ON uw.program_id = p.program_id
INNER JOIN workouts w ON w.workout_id = uw.workout_id
WHERE uw.program_id = 1 AND uw.user_id = 8;

select * from user_workouts where user_id = 8 and program_id = 1;

SELECT p.program_id, w.workout_id, w.workout_name, w.workout_location 
FROM program_workouts pw  
JOIN programs p ON pw.program_id = p.program_id  
JOIN workouts w ON pw.workout_id = w.workout_id  
JOIN userPrograms up ON up.program_id = p.program_id  
JOIN user_workouts uw ON up.program_id = uw.program_id AND w.workout_id = uw.workout_id  
WHERE p.program_id = 3 AND up.user_id = 8;


CREATE TABLE user_workouts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  workout_id INT,
  program_id INT,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
  FOREIGN KEY (program_id) REFERENCES programs(program_id)
);

ALTER TABLE workouts
ADD workout_type varchar(225);


SELECT DISTINCT p.program_id, w.workout_id, w.workout_name, w.workout_location  
       FROM program_workouts pw  
       JOIN programs p ON pw.program_id = p.program_id  
       JOIN workouts w ON pw.workout_id = w.workout_id  
       JOIN userPrograms up ON up.program_id = p.program_id
       WHERE p.program_id = 3 AND up.user_id = 8;