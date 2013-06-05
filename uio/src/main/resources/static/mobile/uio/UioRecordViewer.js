Ext.define('uio.UioRecordViewer', {
    extend: 'Ext.Panel',
    xtype: 'uiorecordviewer',
   
    
  constructor: function(config)
    {
	  var closeButton = {ui: 'back', text: 'Back',handler: function(button){  
		  var viewer = button.up("uiorecordviewer");
		  viewer.destroy();
	  }};

	  var mydata = [];
	 
	  for(var attr in config.data)
	  {
		  mydata.push({name: attr, value: config.data[attr]});
	  }
	  
	  var fieldsList = {xtype: 'list', store: {fields: ["name", "value"], data: mydata}, itemTpl: "<b>{name}<b>: {value}"};
	  config.items = [{docked: 'top', xtype: 'toolbar', title: uio.formdefs[config.jclass].title, items:[closeButton]}, fieldsList];
	  config.layout="fit";
	  this.callParent(arguments);
    }
});
