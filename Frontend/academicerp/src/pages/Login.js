import React, { useState } from 'react'
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
export default function Login() {

    let navigate=useNavigate()
    const [user,setUser]=useState(
        {
            email:"",
            password:"",
        }
    )
    const{email,password}=user

    const onInputChange=(e)=>{
        const {id,value}=e.target;
        setUser({ ...user, [id]: value });

    };
    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/v1/auth/admin/login", user,{
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("jwt")}`,
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                },
            });
            localStorage.setItem("jwt", response.data.jwt);
            navigate("/home"); 
        } catch (error) {
            alert("Invalid Email or Password!");
            console.error("Login failed:", error.message);
        }
    };
  return (
    <div className="container">
        <div className="row mt-3">
            <div className='col-md-6 offset-md-3 border newMargin rounded p-4 mt-2 shadow'>
            
                <h2 className='text-center mt-3'>Welcome to IIITB Academia</h2>
                <h3 className='text-center m-4'>Login</h3>
                <form onSubmit={(e)=>onSubmit(e)}>
                <div className='mb-3'>
                    <label htmlFor='Name' className='form-label'>
                        E-mail ID:
                    </label>
                    <input type="text" className="form-control" placeholder="Enter Email Id" id="email" value={email} onChange={(e) => onInputChange(e)} />
                </div>
                <div className='mb-3'>
                    <label htmlFor='Name' className='form-label'>
                        Password:
                    </label>
                    <input type="password"  className="form-control" placeholder="Enter Password" id="password" value={password} onChange={(e) => onInputChange(e)} />

                </div>
                <button type="submit" className="btn btn-outline-primary">
                Submit
                </button>
                <Link type="submit" className="btn btn-danger mx-2 " to="/">
                Cancel
                </Link>
                </form>
            </div>
        </div>
    </div>
  )
}


