/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.rsf.runtime.client;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import net.hasor.rsf.protocol.message.ResponseMsg;
/**
 * 接受Response响应，并交付Response处理线程处理。
 * @version : 2014年11月4日
 * @author 赵永春(zyc@hasor.net)
 */
class InnerClientHandler extends ChannelInboundHandlerAdapter {
    private RsfClientFactory rsfClientFactory = null;
    //
    public InnerClientHandler(RsfClientFactory rsfClientFactory) {
        this.rsfClientFactory = rsfClientFactory;
    }
    //
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ResponseMsg == false)
            return;
        ResponseMsg responseMsg = (ResponseMsg) msg;
        InnerAbstractRsfClient rsfClient = this.rsfClientFactory.getRsfClient(ctx.channel());
        RsfFuture rsfFuture = rsfClient.getRequest(responseMsg.getRequestID());
        if (rsfFuture == null) {
            return;//或许它已经超时了。
        }
        //送入队列
        try {
            Executor exe = this.rsfClientFactory.getExecutor();
            exe.execute(new InnerResponseHandler(responseMsg, rsfClient, rsfFuture));
        } catch (RejectedExecutionException e) {
            // TODO: handle exception
        }
    }
}