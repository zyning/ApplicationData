package application.dao;

import application.domain.Applications;
import application.domain.ApplicationsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplicationsMapper {
    int countByExample(ApplicationsExample example);

    int deleteByExample(ApplicationsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Applications record);

    int insertSelective(Applications record);

    List<Applications> selectByExample(ApplicationsExample example);

    Applications selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Applications record, @Param("example") ApplicationsExample example);

    int updateByExample(@Param("record") Applications record, @Param("example") ApplicationsExample example);

    int updateByPrimaryKeySelective(Applications record);

    int updateByPrimaryKey(Applications record);
}