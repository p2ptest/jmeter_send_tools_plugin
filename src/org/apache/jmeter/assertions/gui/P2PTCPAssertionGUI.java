/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/**
 * GUI class supporting the MD5Hex assertion functionality.
 *
 */
package org.apache.jmeter.assertions.gui;

import java.awt.BorderLayout;

import javax.swing.*;

import org.apache.jmeter.assertions.P2PTCPAssertion;
import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;

public class P2PTCPAssertionGUI extends AbstractAssertionGui {

    private static final long serialVersionUID = 240L;

    private JTextArea p2pTcpInput;

    private JRadioButton resBox;

    private JRadioButton equalBox;

    public P2PTCPAssertionGUI() {
        init();
    }

    private void init() {

        setLayout(new BorderLayout(0, 10));
        setBorder(makeBorder());

        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // USER_INPUT
        HorizontalPanel md5HexPanel = new HorizontalPanel();
        md5HexPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                JMeterUtils.getResString("P2PTCP_assertion_md5hex_test"))); // $NON-NLS-1$

        md5HexPanel.add(new JLabel(JMeterUtils.getResString("P2PTCP_assertion_label"))); //$NON-NLS-1$

        p2pTcpInput = new JTextArea(15,80);
        // md5HexInput.addFocusListener(this);
        md5HexPanel.add(p2pTcpInput);

        mainPanel.add(md5HexPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

    }

    @Override
    public void configure(TestElement el) {
        super.configure(el);
        P2PTCPAssertion assertion = (P2PTCPAssertion) el;
        this.p2pTcpInput.setText(String.valueOf(assertion.getP2PTCPTxt()));
    }

    @Override
    public String getLabelResource() {
        return "P2PTCP_assertion_title"; // $NON-NLS-1$
    }

    /*
     * @return
     */
    @Override
    public TestElement createTestElement() {

        P2PTCPAssertion el = new P2PTCPAssertion();
        modifyTestElement(el);
        return el;

    }

    /*
     * @param element
     */
    @Override
    public void modifyTestElement(TestElement element) {
        configureTestElement(element);
        String md5HexString = this.p2pTcpInput.getText();
        // initialize to empty string, this will fail the assertion
        if (md5HexString == null || md5HexString.length() == 0) {
            md5HexString = "";
        }
        ((P2PTCPAssertion) element).setP2PTCPTxt(md5HexString);
    }

    /**
     * Implements JMeterGUIComponent.clearGui
     */
    @Override
    public void clearGui() {
        super.clearGui();

        p2pTcpInput.setText(""); //$NON-NLS-1$
    }
}
