/*
 *  Copyright 2010 jeff.huang.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package demo.schedulor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jeff.huang
 */
public class BeeperControl {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
            }
        };

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {

            @Override
            public void run() {
                beeperHandle.cancel(true);
                scheduler.shutdown();
            }

        }, 60 , TimeUnit.SECONDS);
    }
}
