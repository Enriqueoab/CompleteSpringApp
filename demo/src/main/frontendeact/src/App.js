import React, {useState, useEffect} from "react";
import logo from './logo.svg';
import './App.css';
import axios from "axios";

const UserProfiles = () => {

//Variables and where the variables are going to get their values
const [userProfiles, setUserProfiles] = useState([]);

const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res =>{console.log(res);
    
    const data = res.data;
    //We get the variable state set it
    setUserProfiles(data);
  });
  }
  //If there is any change in the array this function will be execute
  useEffect(() => {
    fetchUserProfiles();

  }, []);
  return userProfiles.map((userProfile,index) =>{
      return (
          <div key={index}>
            <h1>{userProfile.username}</h1>
            <p>{userProfile.userId}</p>
          </div>
      )
  })  
};

function App() {
  return (
    <div className="App">
      <UserProfiles/>
    </div>
  );
}

export default App;
