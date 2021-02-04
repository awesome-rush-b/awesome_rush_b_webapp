package rushb.webapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rushb.webapp.dao.UserDao;
import rushb.webapp.model.User;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByName(username);
        if(user == null){
            logger.warn("User Detail can find this user by username: "+username);
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
        // new ArrayList is for authorities -
        // the authorities that should be granted to the caller if they presented the correct username and password
        // and the user is enabled. Not null.
    }

    /**
     * Bad practice
     * @param user
     * @return
     */
    @Deprecated
    public void save(User user) {
        // todo change it to better practice
        userService.save(user);
    }
}
