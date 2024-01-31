package debug;

import main.Main;

import java.util.ArrayList;

public class DebugMessenger
{
    ArrayList<messageManager> mm;

    public DebugMessenger()
    {
        mm = new ArrayList<messageManager>();
    }

    public void addMessage(Object x)
    {
        mm.add(new messageManager(x));
    }

    class messageManager
    {
        int timer;
        int counter;
        Object message;

        public messageManager(Object message)
        {
            this.message = message;
        }

        public void update()
        {
            counter++;
            if(counter >= timer)
            {
                counter -= timer;

                if(message != null) System.out.println(message);
            }
        }
    }

    class sysTick implements Runnable
    {
        Thread timeUpdate;

        public sysTick()
        {
            timeUpdate = new Thread(this);

            timeUpdate.start();
        }

        @Override
        public void run()
        {
            double targetFPS = 1000000000.0 / Main.gw.targetFPS;
            double delta = 0.00;
            long currTime;
            long prevTime = System.nanoTime();

            while(timeUpdate.getState() == Thread.State.RUNNABLE)
            {
                currTime = System.nanoTime();
                delta += (prevTime - currTime) / targetFPS;
                prevTime = currTime;

                if(delta >= 1)
                {
                    delta--;

                    for(int i = 0; i < mm.size(); i++)
                    {
                        mm.get(i).update();
                    }
                }
            }
        }
    }
}
