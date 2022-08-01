package com.hiwei.test;
import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;
public class Test {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        System.out.println("main 方法执行");
        System.out.println(DemoService.add(10,3));
        System.out.println(DemoService.delete());
        System.out.println(DemoService.getUser());

        //todo 代理程序的jar包位置
        String agentPath = "D:\\tools\\repository\\org\\example\\demo-agent\\1.0-SNAPSHOT\\demo-agent-1.0-SNAPSHOT.jar";
//获取所有实例
        List<VirtualMachineDescriptor> descriptorList = VirtualMachine.list();
        for (VirtualMachineDescriptor descriptor : descriptorList) {
//判断如果是主程序，就加载代理程序
            if (descriptor.displayName().equals(Test.class.getName())) {
                VirtualMachine virtualMachine = VirtualMachine.attach(descriptor);
                virtualMachine.loadAgent(agentPath);
            }
        }
    }
}
