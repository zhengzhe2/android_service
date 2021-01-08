// IMyServer.aidl
package com.example.servicedemo;

// Declare any non-default types here with import statements
import com.example.servicedemo.service.Student;
interface IMyServer {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void say(String word);
    int tell(String word, int age);
    void getStudentInfo(int age, in Student student);
}