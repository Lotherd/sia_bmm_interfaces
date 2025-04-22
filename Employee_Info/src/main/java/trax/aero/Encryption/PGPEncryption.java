package trax.aero.Encryption;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;

import trax.aero.logger.LogManager;



public final class PGPEncryption {

	private static final String encryptionPassphrase = System.getProperty("passphrase"); 
	private static final String encryptionFile =System.getProperty("keyFile");
	
	static Logger logger = LogManager.getLogger("EmployeeInfo_01");
	
	 public static void decryptFile(
	            String inputFileName,
	            String keyFileName,
	            char[] passwd,
	            String defaultFileName, String folder)
	            throws IOException, NoSuchProviderException,Exception
	    {
		 
	        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));

	        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));
	        decryptFile(in, keyIn, passwd, defaultFileName,folder);
	        keyIn.close();
	        in.close();
	    }

	    /**
	     * decrypt the passed in message stream
	     * @throws Exception 
	     */
	    private static void decryptFile(
	            InputStream in,
	            InputStream keyIn,
	            char[]      passwd,
	            String      defaultFileName,
	            String folder)
	            throws Exception
	    {
	        in = PGPUtil.getDecoderStream(in);
	        
	        try
	        {
	            JcaPGPObjectFactory        pgpF = new JcaPGPObjectFactory(in);
	            PGPEncryptedDataList    enc;

	            Object                  o = pgpF.nextObject();
	            //
	            // the first object might be a PGP marker packet.
	            //
	            if (o instanceof PGPEncryptedDataList)
	            {
	                enc = (PGPEncryptedDataList)o;
	            }
	            else
	            {
	                enc = (PGPEncryptedDataList)pgpF.nextObject();
	            }
	           
	            Collection<PGPSecretKeyRing> colllection = new ArrayList<PGPSecretKeyRing>();
	            PGPObjectFactory factory = new PGPObjectFactory(PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());
	    		for (Iterator iter = factory.iterator(); iter.hasNext();) {
	    			Object section = iter.next();
	    			

	    			if (section instanceof PGPSecretKeyRing) {
	    				colllection.add((PGPSecretKeyRing) section);
	    			}
	    		}
	           
	            //
	            // find the secret key
	            //
	            Iterator                    it = enc.getEncryptedDataObjects();
	            
	            PGPPrivateKey               sKey = null;
	            PGPPublicKeyEncryptedData   pbe = null;
	            
	            PGPSecretKeyRingCollection  pgpSec = new PGPSecretKeyRingCollection(
	            		colllection);
	            
	            
	         
	            
	            
	            while (sKey == null && it.hasNext())
	            {
	                pbe = (PGPPublicKeyEncryptedData)it.next();

	                sKey = PGPUtils.findSecretKey(pgpSec, pbe.getKeyID(), passwd);
	            }

	            if (sKey == null)
	            {
	                throw new IllegalArgumentException("secret key for message not found.");
	            }

	            InputStream         clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));

	            JcaPGPObjectFactory    plainFact = new JcaPGPObjectFactory(clear);

	            PGPCompressedData   cData = (PGPCompressedData)plainFact.nextObject();

	            InputStream         compressedStream = new BufferedInputStream(cData.getDataStream());
	            JcaPGPObjectFactory    pgpFact = new JcaPGPObjectFactory(compressedStream);

	            Object              message = pgpFact.nextObject();

	            
	            
	            if (message instanceof PGPLiteralData)
	            {
	                PGPLiteralData ld = (PGPLiteralData)message;

	                String outFileName = ld.getFileName();
	                	                
	                if (outFileName.length() == 0)
	                {
	                    outFileName = defaultFileName;
	                }
	                
	                
	                if(!outFileName.contains(".csv")) {
	                	outFileName = outFileName + ".csv";
	                }
	                logger.info("Output file:" + outFileName);
	                InputStream unc = ld.getInputStream();
	                OutputStream fOut =  new BufferedOutputStream(new FileOutputStream(folder+File.separator + outFileName));
	               
	                              
	                
	                Streams.pipeAll(unc, fOut);

	                fOut.close();
	            }
	            else if (message instanceof PGPOnePassSignatureList)
	            {
	                throw new PGPException("encrypted message contains a signed message - not literal data.");
	            }
	            else
	            {
	                throw new PGPException("message is not a simple encrypted file - type unknown.");
	            }

	            if (pbe.isIntegrityProtected())
	            {
	                if (!pbe.verify())
	                {
	                	logger.severe("message failed integrity check");
	                }
	                else
	                {
	                	logger.info("message integrity check passed");
	                }
	            }
	            else
	            {
	            	logger.info("no message integrity check");
	            }
	        }
	        catch (PGPException e)
	        {
	        	logger.severe(e.toString());
	        	in.close();
	        	keyIn.close();
	        	throw new Exception("Could not decrypt file ERROR: "+e.toString());
	        }
	    }

	    private static void encryptFile(
	            String          outputFileName,
	            String          inputFileName,
	            String          encKeyFileName,
	            boolean         armor,
	            boolean         withIntegrityCheck)
	            throws IOException, NoSuchProviderException, PGPException
	    {
	        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
	        PGPPublicKey encKey = PGPUtils.readPublicKey(encKeyFileName);
	        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);
	        out.close();
	    }

	    private static void encryptFile(
	            OutputStream    out,
	            String          fileName,
	            PGPPublicKey    encKey,
	            boolean         armor,
	            boolean         withIntegrityCheck)
	            throws IOException, NoSuchProviderException
	    {
	        if (armor)
	        {
	            out = new ArmoredOutputStream(out);
	        }

	        try
	        {
	            PGPEncryptedDataGenerator   cPk = new PGPEncryptedDataGenerator(new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));

	            cPk.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));

	            OutputStream                cOut = cPk.open(out, new byte[1 << 16]);

	            PGPCompressedDataGenerator  comData = new PGPCompressedDataGenerator(
	                    PGPCompressedData.ZIP);

	            PGPUtil.writeFileToLiteralData(comData.open(cOut), PGPLiteralData.BINARY, new File(fileName), new byte[1 << 16]);

	            comData.close();

	            cOut.close();

	            if (armor)
	            {
	                out.close();
	            }
	        }
	        catch (PGPException e)
	        {
	            System.err.println(e);
	            if (e.getUnderlyingException() != null)
	            {
	                e.getUnderlyingException().printStackTrace();
	            }
	        }
	    }

		public static String getEncryptionpassphrase() {
			return encryptionPassphrase;
		}

		public static String getEncryptionfile() {
			return encryptionFile;
		}
	    
	    
	    
	    

}
