

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infotech.client.RegionDAO;
import com.infotech.client.TableAccessHelper;
import com.infotech.model.RegionModel;
import com.microsoft.azure.storage.StorageException;

public class RegionDAOTest {

    @Mock
    private TableAccessHelper<RegionModel> accessHelper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void test() throws InvalidKeyException, URISyntaxException, StorageException
    {
    	RegionDAO dao = new RegionDAO(this.accessHelper);
    	
    	doNothing().when(this.accessHelper).save(isA(RegionModel.class));

    	dao.SaveRegion(new RegionModel("001", "abc"));
    	
    	verify(this.accessHelper, times(1)).save(isA(RegionModel.class));
    }
}
