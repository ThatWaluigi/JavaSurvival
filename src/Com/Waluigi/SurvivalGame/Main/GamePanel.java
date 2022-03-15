package Com.Waluigi.SurvivalGame.Main;

import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Storage.BiomeList;
import Com.Waluigi.SurvivalGame.Main.Tools.Tool;
import Com.Waluigi.SurvivalGame.Main.Util.RenderSys;
import Com.Waluigi.SurvivalGame.Main.World.Biome;
import Com.Waluigi.SurvivalGame.Main.World.Chunk;
import Com.Waluigi.SurvivalGame.Main.World.Spawnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import static Com.Waluigi.SurvivalGame.Main.Storage.ToolList.*;

public class GamePanel extends JPanel implements ActionListener {
    public static final String IMAGE_PATH = "Com/Waluigi/SurvivalGame/Resources/Images/";
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int GRID_SIZE = 50;
    public static final int GRID_X = SCREEN_WIDTH/GRID_SIZE;
    public static final int GRID_Y = SCREEN_HEIGHT/GRID_SIZE;
    static final int DELAY = 60;
    public final int SLOTS = 15;
    Player player = new Player("Player.png");
    public float PlayerX = 0;
    public float PlayerY = 0;
    int LastPOSX;
    int LastPOSY;
    public ArrayList<Item> Inventory = new ArrayList<>();
    boolean InventoryOpen = false;
    public float MoveCooldown;
    static final float COOLDOWN = 0.35F;
    public Chunk CurrentChunk;
    ArrayList<Chunk> Map = new ArrayList<>();
    boolean isRunning = false;
    Tool[] tools = new Tool[]{PICKAXE.Tool, AXE.Tool, SHOVEL.Tool};
    public Tool PlayersTool = tools[0];
    Random random;
    RenderSys sys;
    Timer timer;

    public Chunk GetChunk(int X, int Y) {
        for (Chunk chunk : Map) {
            if (chunk.X == X && chunk.Y == Y) {
                return chunk;
            }
        }
        return null;
    }

    GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new GamePanel.GameKeyListener());
    }

    public void Collisions() {
        if (PlayerX < 0) {
            PlayerX = GRID_X-1;
            if (GetChunk(CurrentChunk.X - 1, CurrentChunk.Y) != null) {
                CurrentChunk = GetChunk(CurrentChunk.X - 1, CurrentChunk.Y);
            } else {
                CurrentChunk = new Chunk(CurrentChunk.X - 1, CurrentChunk.Y, RandomBiome());
                CurrentChunk.GenerateChunk(4, 3, 9);
                Map.add(CurrentChunk);
            }
        }

        if (PlayerY < 0) {
            PlayerY = GRID_Y-1;
            if (GetChunk(CurrentChunk.X, CurrentChunk.Y - 1) != null) {
                CurrentChunk = GetChunk(CurrentChunk.X, CurrentChunk.Y - 1);
            } else {
                CurrentChunk = new Chunk(CurrentChunk.X, CurrentChunk.Y - 1, RandomBiome());
                CurrentChunk.GenerateChunk(4, 3, 9);
                Map.add(CurrentChunk);
            }
        }

        if (PlayerX > GRID_X-1) {
            PlayerX = 0;
            if (GetChunk(CurrentChunk.X + 1, CurrentChunk.Y) != null) {
                CurrentChunk = GetChunk(CurrentChunk.X + 1, CurrentChunk.Y);
            } else {
                CurrentChunk = new Chunk(CurrentChunk.X + 1, CurrentChunk.Y, RandomBiome());
                CurrentChunk.GenerateChunk(4, 3, 9);
                Map.add(CurrentChunk);
            }
        }

        if (PlayerY > GRID_Y-1) {
            PlayerY = 0;
            if (GetChunk(CurrentChunk.X, CurrentChunk.Y + 1) != null) {
                CurrentChunk = GetChunk(CurrentChunk.X, CurrentChunk.Y + 1);
            } else {
                CurrentChunk = new Chunk(CurrentChunk.X, CurrentChunk.Y + 1, RandomBiome());
                CurrentChunk.GenerateChunk(4, 3, 9);
                Map.add(CurrentChunk);
            }
        }

        Spawnable Loc = CurrentChunk.ChunkObjects[(int)PlayerX][(int)PlayerY];
        if (Loc != null && Loc.PlayerCollidable) {
            PlayerX = (float)LastPOSX;
            PlayerY = (float)LastPOSY;
        }
    }

    public void StartGame() {
        random = new Random();
        sys = new RenderSys();
        isRunning = true;
        PlayersTool = PICKAXE.Tool;
        CurrentChunk = new Chunk(random.nextInt(1, 5), random.nextInt(1, 5), BiomeList.values()[random.nextInt(BiomeList.values().length)].biome);
        CurrentChunk.GenerateChunk(4, 3, 9);
        Map.add(CurrentChunk);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Biome RandomBiome(){
        return BiomeList.values()[random.nextInt(BiomeList.values().length)].biome;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    public void Draw(Graphics g) {
        g.setColor(null);
        if (CurrentChunk != null) {
            if (CurrentChunk.ChunkBiome.Background != null){
                for (int i = 0; i < GRID_X; i++) {
                    for (int j = 0; j < GRID_Y; j++) {
                        sys.DrawImage(g,i*GRID_SIZE,j*GRID_SIZE, CurrentChunk.ChunkBiome.Background, GRID_SIZE);
                    }
                }
            }
            for(int x = 0; x < CurrentChunk.ChunkObjects.length; ++x) {
                for(int y = 0; y < CurrentChunk.ChunkObjects[x].length; ++y) {
                    Spawnable obj = CurrentChunk.ChunkObjects[x][y];
                    if (obj != null) {
                        sys.DrawCenteredImage(g, x * 50 - 22, y * 50 - 22, obj.ImageFile, 60);
                    }
                }
            }
        }
        g.setColor(Color.GREEN);
        for(int x = 0; x < 12; ++x) {
            g.drawLine(x * 50, 0, x * 50, 600);
            g.drawLine(0, x * 50, 600, x * 50);
        }
        sys.DrawCenteredImage(g, (int)PlayerX * 50 - 22, (int)PlayerY * 50 - 20, player.Filename, 60);
        g.setColor(null);
        PlayersTool.draw(g, (int)PlayerX * 50 - 5, (int)PlayerY * 50 - 20, 30);
        g.setColor(new Color(133, 68, 25, 180));
        if (InventoryOpen) {
            int invX = 0;
            int invY = 400;
            g.fillRect(invX, invY, 125, 200);
            g.setColor(new Color(155, 82, 31, 180));
            g.fillRect(invX + 5, invY + 5, 115, 190);
            Font InvFont = new Font("Comic Sans MS", Font.BOLD, 12);
            g.setFont(InvFont);
            g.setColor(new Color(255, 255, 255, 200));
            sys.DrawCenteredImage(g, invX, invY - 85, sys.getImage("Com/Waluigi/SurvivalGame/Resources/Images/", "Compass.png"), 80);
            String Location = CurrentChunk.X + "," + CurrentChunk.Y;
            int pos = g.getFontMetrics(InvFont).stringWidth(Location);
            g.drawString(Location, (110 - pos) / 2, invY - 30);
            g.setColor(Color.BLACK);

            for(int i1 = 0 ; i1<Inventory.size(); i1++) {
                Item i = Inventory.get(i1);
                g.drawString(i.Name + ": " + i.Amount, invX + 25, invY + 20 + 12 * i1);
                sys.DrawImage(g, invX + 10, invY + 7 + 12 * i1, i.Icon, 15);
            }

            g.setFont(null);
        }

    }

    public void UseTool(int x, int y) {
        if (PlayersTool != null) {
            PlayersTool.UseTool(this, x, y);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            Collisions();
            if (MoveCooldown > 0) {
                MoveCooldown -= 0.1;
                if (MoveCooldown < 0) {
                    MoveCooldown = 0;
                }
            }
        }
        repaint();
    }

    public class GameKeyListener extends KeyAdapter {
        public GameKeyListener() {
        }

        public void keyPressed(KeyEvent e) {
            LastPOSX = (int)PlayerX;
            LastPOSY = (int)PlayerY;
            switch(e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if (MoveCooldown == 0) {
                        PlayerX-=1;
                        MoveCooldown = 0.35F;
                        UseTool((int)PlayerX, (int)PlayerY);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (MoveCooldown == 0) {
                        PlayerX+=1;
                        MoveCooldown = 0.35F;
                        UseTool((int)PlayerX, (int)PlayerY);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (MoveCooldown == 0) {
                        PlayerY+=1;
                        MoveCooldown = COOLDOWN;
                        UseTool((int)PlayerX, (int)PlayerY);
                    }
                    break;
                case KeyEvent.VK_W:
                    if (MoveCooldown == 0) {
                        PlayerY-=1;
                        MoveCooldown = COOLDOWN;
                        UseTool((int)PlayerX, (int)PlayerY);
                    }
                    break;
                case KeyEvent.VK_E:
                    InventoryOpen = !InventoryOpen;
                    break;
                case KeyEvent.VK_Q:
                    PlayersTool = tools[random.nextInt(tools.length)];
                    break;
            }
            Collisions();
        }
    }
}