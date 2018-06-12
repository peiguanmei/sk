package ${package};

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import ${tableClass.fullClassName};

@Repository
public interface ${tableClass.shortClassName}${mapperSuffix} extends ${baseMapper!"Mapper"}<${tableClass.shortClassName}> {

}