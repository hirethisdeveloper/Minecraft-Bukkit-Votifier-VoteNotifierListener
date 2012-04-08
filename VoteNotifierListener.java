import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VoteListener;

public class VoteNotifierListener implements VoteListener {

        public static final String FILE = "./plugins/Votifier/votes.log";
        private static final Logger log = Logger.getLogger("VoteNotifierListener");

        @Override
        public void voteMade(Vote vote) {

                final String username = vote.getUsername();
                final String datetime = vote.getTimeStamp();

                try {

                        // Open a buffered writer in append mode.
                        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true));

                        // Append the vote to the file.
                        writer.write(vote.toString());
                        writer.newLine();
                        writer.flush();

                        // All done.
                        writer.close();

                        // Tell the player how awesome they are.
                        final Player player = Bukkit.getServer().getPlayer(username);
                        if (player != null) {
                                player.sendMessage("Thanks for voting " + username + "!");
                        }
                        
                        final Server srv = Bukkit.getServer();
                        srv.broadcastMessage( username + " just voted for us!");

                } catch (Exception ex) {
                        System.out.println("[Votifier] unable to vote: " + ex.getMessage());
                }
        }
}

