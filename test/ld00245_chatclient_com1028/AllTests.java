package ld00245_chatclient_com1028;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ EncrypterUnitTest.class, MessageCaptureTest.class, MessageRelayTest.class})
public class AllTests {
}
