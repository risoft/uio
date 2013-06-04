Ext.define('uio.UioGrid', {
    extend: 'Ext.dataview.List',
    requires: ['uio.UioRecordViewer'],
 
    xtype: 'uiogrid',
    fullscreen: true,
    disclosureProperty: 'name',
    onItemDisclosure: true,
    itemTpl: '{name}',
    
    detailCard: {
        xtype: 'container',
        layout: 'fit'
    },

    
    select: function(record, arg2, arg3)
    {
    	var recordViewer = Ext.create("uio.UioRecordViewer", {jclass:this.config.jclass, data: record.data});
    	Ext.Viewport.add(recordViewer);
    	recordViewer.show();
    	this.callParent(arguments);
    },
    
    constructor: function(config)
    {
    	config.store = {
            fields: uio.formdefs[config.jclass].storeFields,
            autoLoad: true,
            proxy: {
            	type: 'ajax',
            	url: '../../'+config.jclass+'/list.json'}
        };  
    	config.autoLoad =true;
        config.itemTpl=  '{name}';

    	config.disclosureProperty='name';
        config.onItemDisclosure={scope: this,handler: function(record, button, index){this.select(record, button, index)}};

     	this.callParent(arguments);
    }
});
