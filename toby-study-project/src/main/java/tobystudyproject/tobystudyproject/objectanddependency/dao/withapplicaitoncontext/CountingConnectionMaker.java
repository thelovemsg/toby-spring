package tobystudyproject.tobystudyproject.objectanddependency.dao.withapplicaitoncontext;

import tobystudyproject.testrefactoring.dao.first.ConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;

/*
* 부가기능 추가!
* DAO가 DB를 얼마나 많이 연결해서 사용하는지 파악하는 오브젝트를 생성.
* */
public class CountingConnectionMaker implements ConnectionMaker {
    int counter = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    //DB connection을 직접 만들지는 않지만 연결시켜줄 때 마다 횟수를 센다.
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException{
        this.counter++;
        return realConnectionMaker.makeConnection();
    }

    public int getCounter(){
        return this.counter;
    }

}
