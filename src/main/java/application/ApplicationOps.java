package application;

import application.dao.ApplicationsMapper;
import application.domain.Applications;
import application.domain.ApplicationsExample;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ApplicationOps {
     public int insertApp(String companyName, String positionName, String jobId, Integer status){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(super.getClass().getClassLoader().getResourceAsStream("spring-mybatis.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApplicationsMapper applicationsMapper = sqlSession.getMapper(ApplicationsMapper.class);

        Applications appTobeInsert = new Applications();
        appTobeInsert.setCompanyName(companyName);
        appTobeInsert.setJobId(jobId);
        appTobeInsert.setPosition(positionName);
        appTobeInsert.setStatus(status);
        Date currentDate = new Date();
        appTobeInsert.setCreateTime(currentDate);
        appTobeInsert.setUpdateTime(currentDate);
        try{
            return applicationsMapper.insert(appTobeInsert);
        } catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }


     public List<Applications> selectByCompanyName(String companyName){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(super.getClass().getClassLoader().getResourceAsStream("spring-mybatis.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApplicationsMapper applicationsMapper = sqlSession.getMapper(ApplicationsMapper.class);

        ApplicationsExample applicationsExample = new ApplicationsExample();
        applicationsExample.createCriteria().andCompanyNameEqualTo(companyName);
        return applicationsMapper.selectByExample(applicationsExample);
    }

    public List<Applications> selectByStatus(Integer status){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(super.getClass().getClassLoader().getResourceAsStream("spring-mybatis.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApplicationsMapper applicationsMapper = sqlSession.getMapper(ApplicationsMapper.class);

        ApplicationsExample applicationsExample = new ApplicationsExample();
        applicationsExample.createCriteria().andStatusEqualTo(status);
        return applicationsMapper.selectByExample(applicationsExample);
    }

    public int deleteById(Integer id){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(super.getClass().getClassLoader().getResourceAsStream("spring-mybatis.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApplicationsMapper applicationsMapper = sqlSession.getMapper(ApplicationsMapper.class);
        return applicationsMapper.deleteByPrimaryKey(id);
    }
}
