import React, {useState, useEffect,useCallback} from "react";
import {useDropzone} from 'react-dropzone';
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
            <MyDropfilezone/>
            <h1>{userProfile.username}</h1>
            <p>{userProfile.userId}</p>
          </div>
      )
  })  
};

function MyDropfilezone() {
	const onDrop = useCallback(acceptedFiles => {
		const file = acceptedFiles[0];
    console.log(file);
	}, [])
	const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

	return (
		<div {...getRootProps()}>
		<input {...getInputProps()} />
		{
			isDragActive ?
			<p>Drop the files here ...</p> :
			<p>Drag 'n' drop some files here, or click to select files</p>
		}
		</div>
	)
	}

function App() {
  return (
    <div className="App">
      <UserProfiles/>
    </div>
  );
}

export default App;
