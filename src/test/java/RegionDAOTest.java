

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infotech.client.RegionDAO;
import com.infotech.client.TableAccessHelper;
import com.infotech.model.RegionModel;

public class RegionDAOTest {

    @Mock
    private TableAccessHelper<RegionModel> accessHelper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void test()
    {
    	RegionDAO dao = new RegionDAO(this.accessHelper);
    	
    //when(accessHelper.save(any(RegionModel.class))
    //	when(this.accessHelper.save(any(RegionModel.class)))
    }
}
