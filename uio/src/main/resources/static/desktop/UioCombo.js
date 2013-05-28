

Ext.define('uio.desktop.UioCombo', {
	extend: 'Ext.form.ComboBox',
    queryMode: 'remote',
    displayField: 'display',
    valueField: 'value',
    xtype: 'uiocombo',
    name: 'Pick',
    fieldLabel: 'Emmos',
    initComponent: function()
    {
    	this.callParent();
    	this.store =  Ext.create('Ext.data.Store', {
    	    fields: ['display', 'value'],
    	    proxy: {
    	    	type: 'ajax',
    	    	url: this.jclass+'/combo.json'
    	    		}
    	});
    }
});